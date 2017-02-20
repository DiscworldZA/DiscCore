package disc.mods.core.handlers;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import disc.mods.core.client.gui.inventory.CoreGui;
import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.tile.CoreTileEntity;
import disc.mods.core.tile.CoreTileEntityInventory;

public abstract class GuiHandler implements IGuiHandler
{
	private HashMap<Integer, CoreContainer> containers = new HashMap<Integer, CoreContainer>();
	private HashMap<Integer, CoreGui> guis = new HashMap<Integer, CoreGui>();
	private static int id = 0;

	public GuiHandler()
	{
		RegisterIDs();
	}

	public abstract void RegisterIDs();

	public int RegisterID(CoreGui gui, CoreContainer container)
	{
		int ID = id++;
		containers.put(ID, container);
		guis.put(ID, gui);
		return ID;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof CoreTileEntityInventory)
		{
			CoreTileEntityInventory cte = (CoreTileEntityInventory) tile;
			return containers.get(ID).NewInstance(player.inventory, cte);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof CoreTileEntityInventory)
		{
			CoreTileEntityInventory cte = (CoreTileEntityInventory) tile;
			return guis.get(ID).NewInstance(player.inventory, cte);
		}
		return null;
	}

}