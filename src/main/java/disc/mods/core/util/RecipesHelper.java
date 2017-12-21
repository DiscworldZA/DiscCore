package disc.mods.core.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class RecipesHelper {
	public static IRecipe GetRegisteredRecipe(Item item) {
		ItemStack stack = new ItemStack(item);
		for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
			if (recipe.getRecipeOutput().isItemEqual(stack)) {
				return recipe;
			}
		}
		return null;
	}
}
