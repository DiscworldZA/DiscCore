package disc.mods.core.util;

import disc.mods.core.block.CoreBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;

public enum EnumSide {
	Down, Up, Front, Back, Left, Right;

	public BlockPos getBlockAtSide(TileEntity te) {
		EnumFacing facing = te.getWorld().getBlockState(te.getPos()).getValue(CoreBlock.FACING);

		switch (this) {
		case Back:
			facing = facing.getOpposite();
			break;
		case Down:
			facing = this.getDownFacingFrom(facing);
			break;
		case Front:
			break;
		case Left:
			facing = facing.rotateY();
			break;
		case Right:
			facing = facing.rotateYCCW();
			break;
		case Up:
			facing = this.getDownFacingFrom(facing).getOpposite();
			break;
		}
		return te.getPos().add(facing.getDirectionVec());
	}

	private EnumFacing getDownFacingFrom(EnumFacing facing) {
		switch (facing) {
		case EAST:
			return facing.rotateAround(Axis.X).rotateAround(Axis.Z);
		case NORTH:
			return facing.rotateAround(Axis.Z).rotateAround(Axis.X);
		case SOUTH:
			return facing.rotateAround(Axis.Z).rotateAround(Axis.X).getOpposite();
		case WEST:
			return facing.rotateAround(Axis.X).rotateAround(Axis.Z).getOpposite();
		case DOWN:
			return facing;
		case UP:
			return facing.getOpposite();
		}
		return facing;
	}

	public boolean matches(EnumFacing facing, TileEntity te) {
		return this.getBlockAtSide(te).equals(te.getPos().add(facing.getDirectionVec()));
	}
}
