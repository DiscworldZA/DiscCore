package disc.mods.core.items;

import disc.mods.core.DiscMod;
import disc.mods.core.creativetab.Tabs;
import disc.mods.core.util.IItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoreItem extends Item implements IItemRenderer {
	private final String resourcePath;
	public String Name;

	public CoreItem(String name, String resourcePath) {
		this.setUnlocalizedName(name);
		this.resourcePath = resourcePath;
		this.Name = name;
		this.setCreativeTab(Tabs.ItemsTab);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s.%s", this.getRegistryName().getResourceDomain(),
				this.getRegistryName().getResourcePath());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerItemRenderer() {
		final String resourcePath = String.format("%s:%s", DiscMod.instance().getModId(), this.resourcePath);

		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(resourcePath, "inventory"));
	}
}
