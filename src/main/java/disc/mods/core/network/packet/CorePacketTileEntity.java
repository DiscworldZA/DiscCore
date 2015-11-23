package disc.mods.core.network.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import disc.mods.core.tile.CoreTileEntity;

public abstract class CorePacketTileEntity implements IMessage
{
	public int x, y, z, meta;

	public CorePacketTileEntity()
	{

	}

	public CorePacketTileEntity(CoreTileEntity tile)
	{
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.meta = tile.getBlockMetadata();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.meta = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(meta);
	}

}
