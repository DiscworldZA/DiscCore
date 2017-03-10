package disc.mods.core.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Tabs
{
    public static final CreativeTabs CoreBlocks = new CreativeTabs(
            "dc.CoreBlocks")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.GUNPOWDER);
        }

        @Override
        public boolean hasSearchBar()
        {
            return true;
        }
    };

    public static final CreativeTabs CoreItems = new CreativeTabs(
            "dc.CoreItems")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.APPLE);
        }

        @Override
        public boolean hasSearchBar()
        {
            return true;
        }
    };
}
