package disc.mods.core.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Tabs {
	public static final CreativeTabs BlocksTab = new CreativeTabs("dc.CoreBlocks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.GUNPOWDER);
		}
	};

	public static final CreativeTabs ItemsTab = new CreativeTabs("dc.CoreItems") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.APPLE);
		}
	};
}
