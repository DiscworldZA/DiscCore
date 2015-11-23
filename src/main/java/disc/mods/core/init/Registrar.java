package disc.mods.core.init;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registrar
{
	public static class Register
	{
		/**
		 * Register a block
		 * 
		 * @param block
		 * @param Name
		 */
		public static void Block(Block block, String Name)
		{
			GameRegistry.registerBlock(block, Name);
		}

		/**
		 * Register an Item
		 * 
		 * @param item
		 * @param Name
		 */
		public static void Item(Item item, String Name)
		{
			GameRegistry.registerItem(item, Name);
		}

		public static class Recipes
		{
			/**
			 * Register an IRecipe
			 * 
			 * @param recipe
			 */
			public static void IRecipe(IRecipe recipe)
			{
				GameRegistry.addRecipe(recipe);
			}

			/**
			 * Register a Recipe
			 * 
			 * @param output
			 * @param Qty
			 * @param params
			 */
			public static void Recipe(Item output, int Qty, Object... params)
			{
				GameRegistry.addRecipe(new ItemStack(output, Qty), params);
			}

			/**
			 * Register a smelting recipe
			 * 
			 * @param input
			 * @param output
			 * @param xp
			 */
			public static void Smelting(ItemStack input, ItemStack output, float xp)
			{
				GameRegistry.addSmelting(input, output, xp);
			}

			/**
			 * Register a smelting recipe
			 * 
			 * @param input
			 * @param output
			 * @param xp
			 */
			public static void Smelting(Item input, Item output, float xp)
			{
				Smelting(new ItemStack(input), new ItemStack(output), xp);
			}

			/**
			 * Register a smelting recipe
			 * 
			 * @param input
			 * @param output
			 * @param xp
			 */
			public static void Smelting(Block input, Item output, float xp)
			{
				Smelting(new ItemStack(input), new ItemStack(output), xp);
			}
		}
	}
}
