package disc.mods.core.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class BlockOrientationHelper
{
	
	public static final int MASK_REDSTONE = 0x8;
	public static final int MASK_ORIENTATION = 0x7;
	
	
	public static int setOrientation(int metadata, EnumFacing orientation)
	{
		return (metadata & ~MASK_ORIENTATION) | orientation.ordinal();
	}
	
	public static boolean getRedstoneSignal(int metadata)
	{
		return (metadata & MASK_REDSTONE) != 0;
	}
	
	public static int setRedstoneSignal(int metadata, boolean signal)
	{
		if (signal)
		{
			return metadata | MASK_REDSTONE;
		}
		else
		{
			return metadata & ~MASK_REDSTONE;
		}
	}
	
	public static EnumFacing determineOrientation(int x, int y, int z, EntityLivingBase entityLivingBase)
	{
		if (MathHelper.abs((float) entityLivingBase.posX - x) < 2.0F && MathHelper.abs((float) entityLivingBase.posZ - z) < 2.0F)
		{
			double d0 = entityLivingBase.posY + 1.82D - entityLivingBase.getYOffset();
			
			if (d0 - y > 2.0D)
			{
				return EnumFacing.UP;
			}
			
			if (y - d0 > 0.0D)
			{
				return EnumFacing.DOWN;
			}
		}
		int l = MathHelper.floor((entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? EnumFacing.NORTH : (l == 1 ? EnumFacing.EAST : (l == 2 ? EnumFacing.SOUTH : (l == 3 ? EnumFacing.WEST : EnumFacing.DOWN)));
	}
}
