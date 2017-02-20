package disc.mods.core.network;

import disc.mods.core.helpers.DimensionHelper;
import disc.mods.core.ref.References;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public abstract class CorePacketHandler
{
	public static final SimpleNetworkWrapper SimpleNetwork = NetworkRegistry.INSTANCE
			.newSimpleChannel(References.Mod.ID.toLowerCase());
	public static int discriminator = 0;

	public static <REQ extends IMessage, REPLY extends IMessage> void RegisterMessage(
			Class<? extends IMessageHandler<REQ, REPLY>> c1, Class<REQ> c2, Side side)
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
