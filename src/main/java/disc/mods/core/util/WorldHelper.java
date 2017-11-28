package disc.mods.core.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WorldHelper
{
    public static void SpawnEntity(EntityLiving entity, World world, double x, double y, double z)
    {
        entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
        entity.rotationYawHead = entity.rotationYaw;
        entity.renderYawOffset = entity.rotationYaw;
        world.spawnEntity(entity);
        entity.playLivingSound();
    }
    
    public static void SpawnEntity(EntityLiving entity, World worldIn, BlockPos pos)
    {
        SpawnEntity(entity, worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
}
