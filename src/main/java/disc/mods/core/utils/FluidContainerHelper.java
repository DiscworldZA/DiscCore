package disc.mods.core.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class FluidContainerHelper
{
	public static ItemStack Empty(ItemStack item)
	{
		return FluidContainerRegistry.drainFluidContainer(item);
	}
}
