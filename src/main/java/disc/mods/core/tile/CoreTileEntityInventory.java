package disc.mods.core.tile;

import disc.mods.core.ref.References;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

public abstract class CoreTileEntityInventory extends CoreTileEntity implements IInventory {
	private NonNullList<ItemStack> inventory;
	private int numUsingPlayers;

	public CoreTileEntityInventory() {
		inventory = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
	}

	@Override
	public abstract int getSizeInventory();

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.get(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.set(index, ItemStack.EMPTY);
	}

	@Override
	public ItemStack decrStackSize(int index, int amount) {
		ItemStack itemStack = getStackInSlot(index);
		if (itemStack != null) {
			if (itemStack.getCount() <= amount) {
				setInventorySlotContents(index, null);
			} else {
				itemStack = itemStack.splitStack(amount);
				if (itemStack.getCount() == 0) {
					setInventorySlotContents(index, null);
				}
			}
		}

		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.set(index, stack);

		if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		this.markDirty();

	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.getCustomName() : this.getName();
	}

	@Override
	public boolean hasCustomName() {
		return this.hasCustomName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		++numUsingPlayers;
		world.addBlockEvent(this.pos, this.getBlockType(), 1, numUsingPlayers);

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		--numUsingPlayers;
		world.addBlockEvent(this.pos, this.getBlockType(), 1, numUsingPlayers);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
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
}
