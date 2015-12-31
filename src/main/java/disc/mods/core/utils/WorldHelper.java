package disc.mods.core.utils;

import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WorldHelper
{
	public static void DropFluid(World world, int x, int y, int z, FluidStack stack)
	{
		world.setBlock(x, y, z, stack.getFluid().getBlock());
	}
}
