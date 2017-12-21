package disc.mods.core.proxy;

import disc.mods.core.proxy.base.IProxyClientBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientDiscCoreProxy extends CommonDiscCoreProxy implements IProxyClientBase {

}
