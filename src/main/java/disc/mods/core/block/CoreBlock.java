package disc.mods.core.block;

import disc.mods.core.creativetab.Tabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class CoreBlock extends Block {
	public String Name;

	public CoreBlock(Material materialIn, String Name) {
		super(materialIn);
		this.Name = Name;
		setUnlocalizedName(Name);
		setRegistryName(Name);
		setCreativeTab(Tabs.CoreBlocks);
	}

	public CoreBlock(String Name) {
		this(Material.ROCK, Name);
	}

	@Override
	public CoreBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
