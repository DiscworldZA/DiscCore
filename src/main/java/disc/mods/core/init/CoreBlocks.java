package disc.mods.core.init;

import disc.mods.core.DiscCore;
import disc.mods.core.block.*;
import disc.mods.core.config.CoreConfig;
import disc.mods.core.ref.CoreSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreBlocks
{
    public static CoreBlock TestBlock;

    public static void init()
    {
        if (CoreSettings.Debug.EnableTestBlock.Value)
        {
            TestBlock = register(new TestBlock());
        }
    }

    protected static <T extends Block> T register(T block, ItemBlock itemBlock)
    {
        Registrar.Register(block);
        Registrar.Register(itemBlock);
        registerItemModel(itemBlock);
        return block;
    }

    protected static <T extends Block> T register(T block)
    {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }

    public static void registerItemModel(ItemBlock itemBlock)
    {
        DiscCore.proxy.registerItemRenderer(itemBlock, 0);
    }
}
