package disc.mods.core.tile;

import disc.mods.core.block.CoreBlock;
import disc.mods.core.util.EnumSide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class CoreTileEntityInventory extends CoreTileEntity implements IItemHandler {
	private NonNullList<ItemStack> inventory;
	private int numUsingPlayers;

	public CoreTileEntityInventory() {
		inventory = NonNullList.<ItemStack>withSize(getSlots(), ItemStack.EMPTY);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		ItemStackHelper.loadAllItems(nbtTagCompound, inventory);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		ItemStackHelper.saveAllItems(nbtTagCompound, inventory);

		return nbtTagCompound;
	}

	public NonNullList<ItemStack> getItems() {
		return this.inventory;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return getItems().get(slot);
	}

	protected void setStackInSlot(int slot, ItemStack stack) {
		this.inventory.set(slot, stack);
	}

	public boolean isEmpty() {
		return inventory.stream().allMatch(x -> x.isEmpty());
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		ItemStack stackInSlot = getStackInSlot(slot);
		if (stackInSlot.isEmpty()) {
			if (!simulate) setStackInSlot(slot, stack);
			return ItemStack.EMPTY;
		}
		if (stackInSlot.isItemEqual(stack)) {
			if (stackInSlot.getCount() + stack.getCount() > stackInSlot.getMaxStackSize()) {
				if (!simulate)
					setStackInSlot(slot, new ItemStack(stackInSlot.getItem(), stackInSlot.getMaxStackSize()));
				return new ItemStack(stackInSlot.getItem(),
						stackInSlot.getCount() + stack.getCount() - stackInSlot.getMaxStackSize());
			}
			else {
				if (!simulate) setStackInSlot(slot,
						new ItemStack(stackInSlot.getItem(), stackInSlot.getCount() + stack.getCount()));
				return ItemStack.EMPTY;
			}
		}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		ItemStack stackInSlot = getStackInSlot(slot);
		if (stackInSlot.isEmpty()) return ItemStack.EMPTY;
		if (stackInSlot.getCount() < amount) {
			return null;
		}
		else {
			if (!simulate) setStackInSlot(slot, new ItemStack(stackInSlot.getItem(), stackInSlot.getCount() - amount));
			return new ItemStack(stackInSlot.getItem(), amount);
		}
	}

	@Override
	public int getSlotLimit(int slot) {
		if (getStackInSlot(slot).isEmpty())
			return 64;
		else return getStackInSlot(slot).getMaxStackSize();
	}

	public abstract NonNullList<EnumSide> getItemHandlingSides();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				&& getItemHandlingSides().stream().anyMatch(x -> x.matches(facing, this))) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				&& getItemHandlingSides().stream().anyMatch(x -> x.matches(facing, this))) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

}
