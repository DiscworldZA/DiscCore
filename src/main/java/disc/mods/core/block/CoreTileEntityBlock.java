package disc.mods.core.block;

import disc.mods.core.ref.References;
import disc.mods.core.tile.CoreTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class CoreTileEntityBlock extends CoreBlock {

	public CoreTileEntityBlock(Material material, String Name) {
		super(material, Name);
	}

	public CoreTileEntityBlock(String Name) {
		super(Name);
	}

	@Override
	public abstract TileEntity createTileEntity(World world, IBlockState state);

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	public <T extends CoreTileEntity> T GetTileEntity(IBlockAccess world, BlockPos pos) {
		return (T) world.getTileEntity(pos);
	}

}
