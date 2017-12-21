package disc.mods.core.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Registrar {
	/**
	 * Register a block
	 * 
	 * @param block
	 * @param Name
	 */
	public static void Register(Block block) {
		GameRegistry.register(block);
	}

	/**
	 * Register an Item
	 * 
	 * @param item
	 * @param Name
	 */
	public static void Register(Item item) {
		GameRegistry.register(item);
	}

	/**
	 * Register an ItemBlock
	 * 
	 * @param itemBlock
	 * @param Name
	 */
	public static void Register(ItemBlock itemBlock) {
		GameRegistry.register(itemBlock);
	}

	public static class Recipes {
		/**
		 * Register an IRecipe
		 * 
		 * @param recipe
		 */
		public static void Recipe(IRecipe recipe) {
			GameRegistry.addRecipe(recipe);
		}

		/**
		 * Register a Recipe
		 * 
		 * @param output
		 * @param Qty
		 * @param params
		 */
		public static void Recipe(Item output, int Qty, Object... params) {
			GameRegistry.addRecipe(new ItemStack(output, Qty), params);
		}

		/**
		 * Register a smelting recipe
		 * 
		 * @param input
		 * @param output
		 * @param xp
		 */
		public static void Smelting(ItemStack input, ItemStack output, float xp) {
			GameRegistry.addSmelting(input, output, xp);
		}

		/**
		 * Register a smelting recipe
		 * 
		 * @param input
		 * @param output
		 * @param xp
		 */
		public static void Smelting(Item input, Item output, float xp) {
			Smelting(new ItemStack(input), new ItemStack(output), xp);
		}

		/**
		 * Register a smelting recipe
		 * 
		 * @param input
		 * @param output
		 * @param xp
		 */
		public static void Smelting(Block input, Item output, float xp) {
			Smelting(new ItemStack(input), new ItemStack(output), xp);
		}

		/**
		 * Register a smelting recipe
		 * 
		 * @param input
		 * @param output
		 * @param xp
		 */
		public static void Smelting(Item input, Block output, float xp) {
			Smelting(new ItemStack(input), new ItemStack(output), xp);
		}
	}
}
