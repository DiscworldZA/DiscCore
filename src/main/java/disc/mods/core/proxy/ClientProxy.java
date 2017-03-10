package disc.mods.core.proxy;

import disc.mods.core.ref.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        setModId(event.getModMetadata().modId);
    }

    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(getModId() + ":" + id, "inventory"));
    }

}
