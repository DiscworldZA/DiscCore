package disc.mods.core.inventory;

import disc.mods.core.tile.CoreTileEntity;
import disc.mods.core.tile.CoreTileEntityInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class CoreContainer extends Container {
	private CoreTileEntity tile;
	public int InventoryRowsPlayer = 3;
	public int InventoryColumnsPlayer = 9;

	public void setTile(CoreTileEntity tile) {
		this.tile = tile;
	}

	public void setInventory(InventoryPlayer player) {
		this.drawInv(player, 0, 0);
	}

	public <T> T GetTileEntity() {
		try {
			return (T) tile;
		}
		catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	public void drawInv(InventoryPlayer inventory, int xOffset, int yOffset) {
		for (int row = 0; row < InventoryRowsPlayer; ++row) {
			for (int column = 0; column < InventoryColumnsPlayer; ++column) {
				this.addSlotToContainer(
						new Slot(inventory, column + row * 9 + 9, 8 + xOffset + column * 18, 84 + yOffset + row * 18));
			}
		}

		for (int slot = 0; slot < InventoryColumnsPlayer; ++slot) {
			this.addSlotToContainer(new Slot(inventory, slot, 8 + slot * 18 + xOffset, 142 + yOffset));
		}
	}

	public void addSlot(IInventory stack, int slotIndex, int x, int y) {
		this.addSlotToContainer(new Slot(stack, slotIndex, x, y));
	}
}