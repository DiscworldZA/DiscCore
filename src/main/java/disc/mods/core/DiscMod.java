package disc.mods.core;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import disc.mods.core.proxy.base.IProxyBase;
import disc.mods.core.util.DiscLog;
import disc.mods.core.util.EventHandlerHook;
import disc.mods.core.util.Registrar;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

/***
 * @reference Fireball1725/firelib
 * @author DiscworldZA
 *
 */
public abstract class DiscMod {

//	public static DiscMod instance() {
//		try {
//			DiscCore.instance.getLogger().info("Getting ModContainer");
//			return (DiscMod) Loader.instance().activeModContainer();
//		} catch (Exception e) {
//			DiscCore.instance.getLogger().fatal("instance of mod called that isn't DiscMod??");
//			throw e;
//		}
//	}

	private final DiscLog logger;

	public final DiscLog getLogger() {
		return this.logger;
	}

	public DiscMod() {
		logger = new DiscLog(this);
		logger.info("Initialized");
		EventHandlerHook.Hook(this);
	}

	public abstract String getModId();

	public abstract IProxyBase proxy();

	public Class getBlockEnum() {
		return null;
	}

	public Class getItemEnum() {
		return null;
	}

	@EventHandler
	public final void preInit(FMLPreInitializationEvent event) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		getLogger().info("preInit Started");

		proxy().initConfiguration(event);
		proxy().preInitStart(event);
		proxy().preInitEnd(event);

		getLogger().info("preInit Ending");
		getLogger().info(String.format("preInit Took: %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		this.getLogger().info("init Started");

		proxy().initStart(event);
		proxy().registerCapabilities();
		proxy().registerEventHandlers();
		proxy().initEnd(event);

		getLogger().info("init Ending");
		getLogger().info(String.format("init Took: %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		this.getLogger().info("postInit Started");

		proxy().postInitStart(event);
		proxy().postInitEnd(event);

		getLogger().info("postInit Ending");
		getLogger().info(String.format("postInit Took: %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	@EventHandler
	public final void onServerAboutToStart(FMLServerAboutToStartEvent event) {
		proxy().onServerAboutToStart(event);
	}

	@EventHandler
	public final void onServerStarting(FMLServerStartingEvent event) {
		proxy().onServerStarting(event);
	}

	@EventHandler
	public final void onServerStarted(FMLServerStartedEvent event) {
		proxy().onServerStarted(event);
	}

	@EventHandler
	public final void onServerStopping(FMLServerStoppingEvent event) {
		proxy().onServerStopping(event);
	}

	@EventHandler
	public final void onServerStopped(FMLServerStoppedEvent event) {
		proxy().onServerStopped(event);
	}
}
