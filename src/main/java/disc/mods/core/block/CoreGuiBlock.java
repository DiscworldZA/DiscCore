package disc.mods.core.block;

import disc.mods.core.DiscCore;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CoreGuiBlock extends CoreBlock {
	protected int GuiID;

	public CoreGuiBlock(String name, String resourcePath) {
		super(name, Material.IRON, resourcePath);
	}

	public CoreGuiBlock(String name, Material mat, String resourcePath) {
		super(name, mat, resourcePath);
	}

	public void SetGuiID(int ID) {
		this.GuiID = ID;
	}

	public abstract boolean HasGui();

	public void OpenGui(EntityPlayer player, World world, int x, int y, int z) {
		player.openGui(DiscCore.instance, GuiID, world, x, y, z);
	}
}
