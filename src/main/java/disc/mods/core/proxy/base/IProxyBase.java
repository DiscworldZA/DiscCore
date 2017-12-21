package disc.mods.core.proxy.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;

/***
 * 
 * @author DiscworldZA
 * @reference Fireball1725/firelib
 */
public interface IProxyBase {

	default void preInitStart(FMLPreInitializationEvent e) {

	}

	default void preInitEnd(FMLPreInitializationEvent e) {

	}

	default void initStart(FMLInitializationEvent e) {

	}

	default void initEnd(FMLInitializationEvent e) {

	}

	default void postInitStart(FMLPostInitializationEvent e) {

	}

	default void postInitEnd(FMLPostInitializationEvent e) {

	}

	default void onServerAboutToStart(FMLServerAboutToStartEvent e) {

	}

	default void onServerStarting(FMLServerStartingEvent e) {

	}

	default void onServerStarted(FMLServerStartedEvent e) {

	}

	default void onServerStopping(FMLServerStoppingEvent event) {

	}

	default void onServerStopped(FMLServerStoppedEvent event) {

	}

	void initConfiguration(FMLPreInitializationEvent event);

	void registerEventHandlers();

	void registerCapabilities();

	default void registerEventHandler(Object handler) {
		MinecraftForge.EVENT_BUS.register(handler);
	}

	Side getPhysicalSide();

	Side getEffectiveSide();

	default MinecraftServer getMinecraftServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance();
	}

	EntityPlayer getClientPlayer();

	World getClientWorld();

	World getWorldByDimensionId(int dimension);

	default Entity getEntityById(int dimension, int id) {
		return getEntityById(getWorldByDimensionId(dimension), id);
	}

	default Entity getEntityById(World world, int id) {
		return world == null ? null : world.getEntityByID(id);
	}
}
