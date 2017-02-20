package disc.mods.core.helpers;

import disc.mods.core.ref.References;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper
{
	public static ResourceLocation getResourceLocation(String modId, String path)
	{
		return new ResourceLocation(modId, path);
	}

	public static ResourceLocation getResourceLocation(String path)
	{
		return getResourceLocation(References.Mod.ID.toLowerCase(), path);
	}

	public static ResourceLocation getGuiLocation(String path)
	{
		String GuisLocation = "/textures/gui/";
		return getResourceLocation(GuisLocation + path);
	}

	public static String getBlockLocation(String path)
	{
		String blockLocation = "";
		return References.Mod.ID + ":" + path;
	}
}