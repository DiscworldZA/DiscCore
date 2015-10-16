package disc.mods.core.helpers;

import net.minecraft.util.ResourceLocation;
import disc.mods.core.references.Names;

public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(Names.Mod.ID.toLowerCase(), path);
    }
    
    public static ResourceLocation getGuiLocation(String path)
    {
    	String GuisLocation = "/textures/gui/";
    	return getResourceLocation(GuisLocation + path);
    }
    
    public static String getBlockLocation(String path)
    {
    	String blockLocation = "";
    	return Names.Mod.ID + ":" + path;
    }
}