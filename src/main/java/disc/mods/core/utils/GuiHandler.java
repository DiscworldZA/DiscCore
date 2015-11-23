package disc.mods.core.utils;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sun.xml.internal.bind.v2.model.core.ID;

import cpw.mods.fml.common.network.IGuiHandler;
import disc.mods.core.client.gui.inventory.CoreGui;
import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.tile.CoreTileEntity;

public abstract class GuiHandler implements IGuiHandler
{
	private HashMap<Integer, CoreContainer> containers = new HashMap<Integer, CoreContainer>();
	private HashMap<Integer, CoreGui> guis = new HashMap<Integer, CoreGui>();
	private static int id = 1;

	public GuiHandler()
	{
		RegisterIDs();
	}

	public abstract void RegisterIDs();

	public void RegisterID(CoreGui gui, CoreContainer container)
	{
		int ID = id++;
		containers.put(ID, container);
		guis.put(ID, gui);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof CoreTileEntity)
		{
			CoreTileEntity cte = (CoreTileEntity) tile;
			return containers.get(ID).NewInstance(player.inventory, cte);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof CoreTileEntity)
		{
			CoreTileEntity cte = (CoreTileEntity) tile;
			return guis.get(ID).NewInstance(player.inventory, cte);
		}
		return null;
	}

}
