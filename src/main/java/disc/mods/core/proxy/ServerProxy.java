package disc.mods.core.proxy;

import disc.mods.core.proxy.base.IProxyServerBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy implements IProxyServerBase {

}
