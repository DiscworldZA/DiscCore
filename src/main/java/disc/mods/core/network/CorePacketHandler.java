package disc.mods.core.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import disc.mods.core.references.Names;
import disc.mods.core.utils.DimensionHelper;

public abstract class CorePacketHandler
{
	public static final SimpleNetworkWrapper SimpleNetwork = NetworkRegistry.INSTANCE.newSimpleChannel(Names.Mod.ID.toLowerCase());
	public static int discriminator = 0;

	public static <REQ extends IMessage, REPLY extends IMessage> void RegisterMessage(Class<? extends IMessageHandler<REQ, REPLY>> c1, Class<REQ> c2, Side side)
	{
		SimpleNetwork.registerMessage(c1, c2, discriminator, Side.CLIENT);
		discriminator++;
	}

	public static void sendToServer(IMessage message)
	{
		SimpleNetwork.sendToServer(message);
	}

	public static void sendToAll(IMessage message)
	{
		SimpleNetwork.sendToAll(message);
	}

	public static void SendToEntity(IMessage message, EntityPlayerMP player)
	{
		SimpleNetwork.sendTo(message, player);
	}

	public static void sendToDimension(IMessage message, int dimensionId)
	{
		SimpleNetwork.sendToDimension(message, dimensionId);
	}

	public static void sendToDimension(IMessage message, String DimensionName)
	{
		SimpleNetwork.sendToDimension(message, DimensionHelper.GetDimensionIDFromName(DimensionName));
	}

	public static void sendToAllAround(IMessage message, TargetPoint point)
	{
		SimpleNetwork.sendToAllAround(message, point);
	}

	public static void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range)
	{
		SimpleNetwork.sendToAllAround(message, new TargetPoint(dimension, x, y, z, range));
	}

	public static Packet getPacketFrom(IMessage message)
	{
		return SimpleNetwork.getPacketFrom(message);
	}
}
