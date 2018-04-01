package disc.mods.core;

import com.google.common.base.Stopwatch;
import disc.mods.core.handlers.GuiHandler;
import disc.mods.core.proxy.base.IProxyBase;
import disc.mods.core.util.DiscLog;
import disc.mods.core.util.EventHandlerHook;
import disc.mods.core.util.Registrar;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;

import java.util.concurrent.TimeUnit;

/***
 * @reference Fireball1725/firelib
 * @author DiscworldZA
 *
 */
public abstract class DiscMod {

	public static DiscMod instance() {
		if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
			return (DiscMod) Loader.instance().activeModContainer().getMod();
		} else {
			return DiscCore.instance;
		}
	}

	public static DiscMod instance(String ModId) {
		if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION))
			return DiscCore.instance();
		else {
			return (DiscMod) Loader.instance().getActiveModList().stream().filter(x -> x.getModId().equals(ModId))
					.findFirst().orElseGet(null).getMod();
		}
	}

	private final DiscLog logger;

	private final GuiHandler guiHandler;

	public final DiscLog getLogger() {
		return this.logger;
	}

	public final GuiHandler getGuiHandler() {
		return guiHandler;
	}

	public DiscMod() {
		logger = new DiscLog(this);
		logger.debug("Initialized");
		EventHandlerHook.Hook(this);
		guiHandler = new GuiHandler();
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
		getLogger().debug("preInit Started");

		proxy().registerEventHandler(new Registrar());
		proxy().initConfiguration(event);
		proxy().preInitStart(event);
		proxy().preInitEnd(event);

		getLogger().debug("preInit Ending");
		getLogger().info(String.format("preInit Took: %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		this.getLogger().debug("init Started");

		proxy().initStart(event);
		proxy().registerCapabilities();
		proxy().registerEventHandlers();
		proxy().initEnd(event);

		getLogger().debug("init Ending");
		getLogger().info(String.format("init Took: %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		final Stopwatch stopwatch = Stopwatch.createStarted();
		this.getLogger().debug("postInit Started");

		proxy().postInitStart(event);
		proxy().postInitEnd(event);

		getLogger().debug("postInit Ending");
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
