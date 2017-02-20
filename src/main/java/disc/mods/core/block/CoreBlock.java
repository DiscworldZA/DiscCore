package disc.mods.core.block;

import disc.mods.core.DiscCore;
import disc.mods.core.creativetab.Tabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CoreBlock extends Block
{
	public String Name;

	public CoreBlock(Material materialIn, String Name)
	{
		super(materialIn);
		this.Name = Name;
		setUnlocalizedName(Name);
		setRegistryName(Name);
		setCreativeTab(Tabs.CoreBlocks);
	}

	public CoreBlock(String Name)
	{
		this(Material.ROCK, Name);
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		DiscCore.proxy.registerItemRenderer(itemBlock, 0, Name);
	}

	@Override
	public CoreBlock setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}

}
