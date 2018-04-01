package disc.mods.core.handlers;

import disc.mods.core.client.gui.inventory.CoreGui;
import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.tile.CoreTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class GuiHandler implements IGuiHandler {
	private HashMap<Integer, Class<? extends CoreContainer>> containers = new HashMap<Integer, Class<? extends CoreContainer>>();
	private HashMap<Integer, Class<? extends CoreGui>> guis = new HashMap<Integer, Class<? extends CoreGui>>();
	private static int id = 0;

	public int Register(Class<? extends CoreGui> gui, Class<? extends CoreContainer> container) {
		int ID = id++;
		containers.put(ID, container);
		guis.put(ID, gui);
		return ID;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof CoreTileEntity) {
			CoreTileEntity cte = (CoreTileEntity) tile;
			CoreContainer container = null;
			try {
				container = containers.get(ID).newInstance();
				container.setTile((CoreTileEntity) tile);
				container.setInventory(player.inventory);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return container;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof CoreTileEntity) {
			CoreTileEntity cte = (CoreTileEntity) tile;
			CoreContainer container = null;
			CoreGui gui = null;
			try {
				container = containers.get(ID).newInstance();
				container.setTile((CoreTileEntity) tile);
				container.setInventory(player.inventory);
				gui = guis.get(ID).getConstructor(CoreContainer.class).newInstance(container);
				gui.setTile((CoreTileEntity) tile);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return gui;
		}
		return null;
	}

}
