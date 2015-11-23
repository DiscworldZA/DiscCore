package disc.mods.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import disc.mods.core.references.Names;

@Mod(modid = Names.Mod.ID, name = Names.Mod.Name, version = Names.Mod.Version)
public class DiscCore
{
	@Instance(Names.Mod.ID)
	public static DiscCore instance;

	public static DiscCore getInstance()
	{
		return instance;
	}
}
