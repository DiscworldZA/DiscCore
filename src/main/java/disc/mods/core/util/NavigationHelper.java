package disc.mods.core.util;

import disc.mods.alternativeai.ai.entity.EntityLivingAI;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class NavigationHelper {
	public static boolean NavigateEntityTo(EntityLivingAI entity, BlockPos pos) {
		PathNavigate nav = entity.getNavigator();
		pos = entity.getEntityWorld().getTopSolidOrLiquidBlock(pos);
		boolean flag = nav.tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), entity.getMovementSpeed());
		if (!flag) {
			Vec3d entityVec = new Vec3d(entity.posX, entity.posY, entity.posZ);
			Vec3d destVec = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
			Vec3d entityMovementDirection = destVec.subtract(entityVec).normalize().scale(10);
			BlockPos newPos = entity.getEntityWorld().getTopSolidOrLiquidBlock(new BlockPos(entityMovementDirection.add(entity.getPositionVector())));
			MakeEntityLookAt(entity, newPos);
			return nav.tryMoveToXYZ(newPos.getX(), newPos.getY(), newPos.getZ(), entity.getMovementSpeed());
		} else {
			MakeEntityLookAt(entity, pos);
			return true;
		}
	}

	public static boolean IsEntityCloseTo(EntityLivingAI entity, BlockPos pos) {
		return pos.getDistance((int) entity.posX, (int) entity.posY, (int) entity.posZ) <= 1.5d;
	}

	public static void MakeEntityLookAt(EntityLivingAI entity, BlockPos pos) {
		entity.getLookHelper().setLookPosition(pos.getX(), pos.getY(), pos.getZ(), (float) (entity.getHorizontalFaceSpeed() + 20), (float) entity.getVerticalFaceSpeed());
	}
}
