package disc.mods.core;

import disc.mods.core.init.CoreBlocks;
import disc.mods.core.init.CoreItems;
import disc.mods.core.proxy.IProxy;
import disc.mods.core.proxy.base.IProxyBase;
import disc.mods.core.ref.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = References.Mod.Id, name = References.Mod.Name, version = References.Mod.Version)
public class DiscCore extends DiscMod {

	@Instance
	public static DiscCore instance;

	@SidedProxy(clientSide = References.Proxy.Client, serverSide = References.Proxy.Server)
	public static IProxy proxy;

	@Override
	public String getModId() {
		return References.Mod.Id;
	}

	@Override
	public Class getBlockEnum() {
		return CoreBlocks.class;
	}

	@Override
	public Class getItemEnum() {
		return CoreItems.class;
	}

	@Override
	public IProxyBase proxy() {
		return proxy;
	}

}
