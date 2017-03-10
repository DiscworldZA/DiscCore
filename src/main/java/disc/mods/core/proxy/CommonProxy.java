package disc.mods.core.proxy;

import disc.mods.core.init.CoreBlocks;
import disc.mods.core.init.CoreItems;
import disc.mods.core.ref.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{

    private String ModId;

    public void registerItemRenderer(Item item, int meta, String id)
    {
    }

    public void preInit(FMLPreInitializationEvent event)
    {
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public String getModId()
    {
        return ModId;
    }

    public void setModId(String modId)
    {
        ModId = modId;
    }

}
