package disc.mods.core.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler
{
	public static void RegisterRecipe(IRecipe recipe)
	{
		GameRegistry.addRecipe(recipe);
	}
	
	public static void RegisterSmelting(ItemStack input, ItemStack output, float xp)
	{
		GameRegistry.addSmelting(input, output, xp);
	}
}
