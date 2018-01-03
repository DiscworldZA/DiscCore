package disc.mods.core.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ItemHandlerConverter implements IItemHandler {

	public IInventory inventory;

	public ItemHandlerConverter(IInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public int getSlots() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}

	public void setStackInSlot(int slot, ItemStack stack) {
		inventory.setInventorySlotContents(slot, stack);
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
		return inventory.getInventoryStackLimit();
	}

}
