package disc.mods.core.helpers;

import net.minecraft.world.DimensionType;
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
				DimensionType provider = DimensionManager.getProviderType(i);
				if (provider.getName().equals(Name)) { return provider.getId(); }
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
