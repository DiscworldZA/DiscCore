package disc.mods.core.util;

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
                WorldProvider provider = DimensionManager.getProvider(i);
                if (Name.equals(provider.getDimensionType().getName()))
                {
                    return provider.getDimension();
                }
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
