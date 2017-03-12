package disc.mods.core.items;

import disc.mods.core.DiscCore;
import disc.mods.core.creativetab.Tabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CoreItem extends Item
{
    protected String name;

    public CoreItem(String name)
    {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Tabs.CoreItems);
    }

    public void registerItemModel()
    {
        DiscCore.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public CoreItem setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
}
