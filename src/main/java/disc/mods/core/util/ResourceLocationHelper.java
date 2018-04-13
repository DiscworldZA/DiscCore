package disc.mods.core.util;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
	private static ResourceLocation getResourceLocation(String modId, String path) {
		return new ResourceLocation(modId, path);
	}

	public static ResourceLocation getGuiLocation(String modId, String path) {
		return getResourceLocation(modId, "textures/gui/" + path);
	}

	public static String getBlockLocation(String modId, String path) {
		return modId + ":" + path;
	}

	public static ResourceLocation getEntityLocation(String modId, String path) {
		return getResourceLocation(modId, "textures/entity/" + path);
	}
}