package disc.mods.core.config;

import java.io.File;

import disc.mods.core.DiscCore;
import disc.mods.core.ref.CoreSettings;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CoreConfig extends Configuration {

	public CoreConfig(File file) {
		super(file);
	}

	public void preInit(FMLPreInitializationEvent event) {
		CoreSettings.Load(this);
	}

	public void postInit(FMLPostInitializationEvent event) {
		if (this.hasChanged()) {
			this.save();
		}
	}
}
