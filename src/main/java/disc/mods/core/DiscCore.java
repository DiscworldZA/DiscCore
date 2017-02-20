package disc.mods.core;

import java.util.logging.Logger;

import disc.mods.core.config.CoreConfig;
import disc.mods.core.init.CoreBlocks;
import disc.mods.core.init.CoreItems;
import disc.mods.core.logging.Log;
import disc.mods.core.proxy.CommonProxy;
import disc.mods.core.ref.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.Mod.ID, name = References.Mod.Name, version = References.Mod.Version)
public class DiscCore
{
	@Instance(References.Mod.ID)
	public static DiscCore instance;

	@SidedProxy(serverSide = References.Proxy.Common, clientSide = References.Proxy.Client)
	public static CommonProxy proxy;

	public CoreConfig config;

	public Log Logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Logger
		Logger = new Log(event.getModLog());

		// Config
		config = new CoreConfig(event.getSuggestedConfigurationFile());
		config.preInit(event);

		// Proxy
		proxy.preInit(event);

		// Blocks
		CoreBlocks.init();

		// Items
		CoreItems.init();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		// Proxy
		proxy.Init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Proxy
		proxy.postInit(event);

		// Config
		config.postInit(event);
	}
}
