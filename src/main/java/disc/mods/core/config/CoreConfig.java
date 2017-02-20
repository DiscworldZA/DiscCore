package disc.mods.core.config;

import java.io.File;

import disc.mods.core.DiscCore;
import disc.mods.core.ref.CoreSettings;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.reflect.macros.Universe.RunContextApi;

public class CoreConfig extends Configuration
{
	public static CoreConfig GetConfig()
	{
		return DiscCore.instance.config;
	}
	
	public CoreConfig(File file)
	{
		super(file);
	}
	
	public void preInit(FMLPreInitializationEvent event)
	{
		CoreSettings.Load(this);
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		if(this.hasChanged())
		{
			this.save();
		}
	}
}
