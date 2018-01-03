package disc.mods.core.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class InventoryHelper {

	public static ItemStack Add(IItemHandler itemHandler, ItemStack itemStack) {
		int amountToPut = itemStack.getCount();
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			amountToPut = itemHandler.insertItem(i, new ItemStack(itemStack.getItem(), amountToPut), false).getCount();
			if (amountToPut == 0) return ItemStack.EMPTY;
		}
		return new ItemStack(itemStack.getItem(), amountToPut);
	}
}