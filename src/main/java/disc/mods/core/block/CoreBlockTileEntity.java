package disc.mods.core.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CoreBlockTileEntity extends CoreGuiBlock implements ITileEntityProvider
{
	public CoreBlockTileEntity()
	{
		super(Material.iron);
		this.setHardness(2.5f);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!player.isSneaking())
		{
			if (this.HasGui())
			{
				this.OpenGui(world, player, world, x, y, z);
			}
		}
		return false;
	}
}
