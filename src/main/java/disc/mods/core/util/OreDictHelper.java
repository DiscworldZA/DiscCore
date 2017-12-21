package disc.mods.core.util;

import java.util.Collection;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {
	@Nullable
	public static String getOreDictEquivalent(Collection<ItemStack> itemStacks) {
		if (itemStacks.size() == 0) {
			return null;
		}
		final ItemStack firstStack = itemStacks.iterator().next();

		if (firstStack != null) {
			for (final int oreId : OreDictionary.getOreIDs(firstStack)) {
				return OreDictionary.getOreName(oreId);
			}
		}
		return firstStack.getItem().getRegistryName().getResourcePath();
	}

	public static String getOreDictEquivalent(ItemStack stack) {
		for (final int oreId : OreDictionary.getOreIDs(stack)) {
			return OreDictionary.getOreName(oreId);
		}
		return stack.getItem().getRegistryName().getResourcePath();
	}
}
