package disc.mods.core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disc.mods.core.references.Names;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Names.Mod.ID.toLowerCase());
	public static int discriminator = 0;

	public static <REQ extends IMessage, REPLY extends IMessage> void RegisterMessage(Class<? extends IMessageHandler<REQ, REPLY>> c1, Class<REQ> c2, Side side)
	{
		INSTANCE.registerMessage(c1, c2, discriminator, Side.CLIENT);
		discriminator++;
	}
}
