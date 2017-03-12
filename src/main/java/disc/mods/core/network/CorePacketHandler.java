package disc.mods.core.network;

import java.util.List;

import disc.mods.core.ref.References;
import disc.mods.core.utils.DimensionHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public abstract class CorePacketHandler
{
    public final SimpleNetworkWrapper SimpleNetwork;
    public int discriminator = 0;

    public CorePacketHandler()
    {
        SimpleNetwork = NetworkRegistry.INSTANCE.newSimpleChannel(getModId().toLowerCase());
    }

    public abstract String getModId();

    public <REQ extends IMessage, REPLY extends IMessage> void RegisterMessage(Class<? extends IMessageHandler<REQ, REPLY>> c1, Class<REQ> c2, Side side)
    {
        SimpleNetwork.registerMessage(c1, c2, discriminator, side);
        discriminator++;
    }

    public void sendToServer(IMessage message)
    {
        SimpleNetwork.sendToServer(message);
    }

    public void sendToAll(IMessage message)
    {
        SimpleNetwork.sendToAll(message);
    }

    public void SendToEntity(IMessage message, EntityPlayerMP player)
    {
        SimpleNetwork.sendTo(message, player);
    }

    /**
     * @param message
     * @param listeners
     * @credit gigaherz
     * @see <a href=
     *      "https://github.com/gigaherz/Ender-Rift/blob/master/src/main/java/gigaherz/enderRift/generator/ContainerGenerator.java#L68-L69">link</a>
     */
    public void SendToEntityListeners(IMessage message, List<IContainerListener> listeners)
    {
        listeners.stream().filter(watcher -> watcher instanceof EntityPlayerMP).forEach(watcher -> SimpleNetwork.sendTo(message, (EntityPlayerMP) watcher));
    }

    public void sendToDimension(IMessage message, int dimensionId)
    {
        SimpleNetwork.sendToDimension(message, dimensionId);
    }

    public void sendToDimension(IMessage message, String DimensionName)
    {
        SimpleNetwork.sendToDimension(message, DimensionHelper.GetDimensionIDFromName(DimensionName));
    }

    public void sendToAllAround(IMessage message, TargetPoint point)
    {
        SimpleNetwork.sendToAllAround(message, point);
    }

    public void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range)
    {
        SimpleNetwork.sendToAllAround(message, new TargetPoint(dimension, x, y, z, range));
    }

    public Packet getPacketFrom(IMessage message)
    {
        return SimpleNetwork.getPacketFrom(message);
    }
}
