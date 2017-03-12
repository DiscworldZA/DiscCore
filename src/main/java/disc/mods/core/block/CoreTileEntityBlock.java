package disc.mods.core.block;

import disc.mods.core.ref.Names;
import disc.mods.core.tile.CoreTileEntity;
import net.minecraft.block.ITileEntityProvider;
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

public abstract class CoreTileEntityBlock extends CoreBlock
{
    public static final PropertyDirection FACING = PropertyDirection.create(Names.NBT.Direction, EnumFacing.Plane.HORIZONTAL);

    public CoreTileEntityBlock(Material material, String Name)
    {
        super(material, Name);
        this.setDefaultState(this.blockState.getBaseState());
    }

    public CoreTileEntityBlock(String Name)
    {
        super(Name);
        this.setDefaultState(this.blockState.getBaseState());
    }

    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);

    public <T extends CoreTileEntity> T GetTileEntity(IBlockAccess world, BlockPos pos)
    {
        return (T) world.getTileEntity(pos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex() - 2;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }
}
