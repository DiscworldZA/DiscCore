package disc.mods.core.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientDiscCoreProxy extends CommonDiscCoreProxy {
	public void preInit(FMLPreInitializationEvent event) {
		setModId(event.getModMetadata().modId);
	}

	public void registerItemRenderer(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(item.getRegistryName(), "direction=north"));
	}

}
