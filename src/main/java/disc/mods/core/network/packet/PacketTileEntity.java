package disc.mods.core.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketTileEntity implements IMessage
{
	
	public int x, y, z;
	
	public PacketTileEntity()
	{
		
	}
	
	public PacketTileEntity(TileEntity tile)
	{
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;;
	}
	

	@Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

	@Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

}
