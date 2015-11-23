package disc.mods.core.utils;

import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class DimensionHelper
{
	public static int GetDimensionIDFromName(String Name)
	{
		try
		{
			for (int i : DimensionManager.getIDs())
			{
				WorldProvider provider;
				if ((provider = DimensionManager.getProvider(i)).getDimensionName().equals(Name)) { return provider.dimensionId; }
			}
			throw new Exception("Dimension not found");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return (int) Double.NaN;
	}
}
