package disc.mods.core.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CoreGuiBlock extends CoreBlock
{
	public CoreGuiBlock(Material mat)
	{
		super(mat);
	}

	public abstract int GetGuiID();

	public abstract boolean HasGui();

	public void OpenGui(Object mod, EntityPlayer player, World world, int x, int y, int z)
	{
		player.openGui(mod, GetGuiID(), world, x, y, z);
	}
}
