package disc.mods.core.tile;

import disc.mods.core.ref.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class CoreTileEntityInventory extends CoreTileEntity implements IInventory {
	protected ItemStack[] inventory;
	private int numUsingPlayers;

	public void createInventory(int size) {
		inventory = new ItemStack[size];
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
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
		inventory[index] = stack;

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

		// Read in the ItemStacks in the inventory from NBT
		NBTTagList tagList = nbtTagCompound.getTagList(References.NBT.Items, 10);
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			byte slotIndex = tagCompound.getByte("Slot");
			if (slotIndex >= 0 && slotIndex < inventory.length) {
				inventory[slotIndex] = new ItemStack(tagCompound);
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		// Write the ItemStacks in the inventory to NBT
		NBTTagList tagList = new NBTTagList();
		for (int slotIndex = 0; slotIndex < inventory.length; ++slotIndex) {
			if (inventory[slotIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) slotIndex);
				inventory[slotIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag(References.NBT.Items, tagList);
		return nbtTagCompound;
	}
}
