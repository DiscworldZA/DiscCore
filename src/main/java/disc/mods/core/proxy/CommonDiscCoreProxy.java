package disc.mods.core.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonDiscCoreProxy {
	private String ModId;

	public void registerItemRenderer(Item item, int meta) {
	}

	public void preInit(FMLPreInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public String getModId() {
		return ModId;
	}

	public void setModId(String modId) {
		ModId = modId;
	}

}
