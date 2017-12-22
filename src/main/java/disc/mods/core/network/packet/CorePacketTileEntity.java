package disc.mods.core.network.packet;

import disc.mods.core.tile.CoreTileEntity;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class CorePacketTileEntity implements IMessage {
	public int x, y, z, meta;

	public CorePacketTileEntity() {

	}

	public CorePacketTileEntity(CoreTileEntity tile) {
		this.x = tile.getPos().getX();
		this.y = tile.getPos().getY();
		this.z = tile.getPos().getZ();
		this.meta = tile.getBlockMetadata();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.meta = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(meta);
	}

}
