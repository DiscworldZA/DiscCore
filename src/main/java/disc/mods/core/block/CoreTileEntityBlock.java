package disc.mods.core.block;

import javax.annotation.Nonnull;

import disc.mods.core.DiscMod;
import disc.mods.core.tile.CoreTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public abstract class CoreTileEntityBlock extends CoreBlock implements ITileEntityProvider {

	@Nonnull
	private Class<? extends TileEntity> tileEntityClass;

	public CoreTileEntityBlock(String name, String resourcePath) {
		super(name, Material.IRON, resourcePath);
	}

	public CoreTileEntityBlock(String name, Material mat, String resourcePath) {
		super(name, mat, resourcePath);
	}

	public <T extends CoreTileEntity> T GetTileEntity(IBlockAccess world, BlockPos pos) {
		return (T) world.getTileEntity(pos);
	}

	protected void setTileEntity(final Class<? extends TileEntity> clazz) {
		this.tileEntityClass = clazz;
		this.setTileProvider(true);

		String tileName = "tileentity." + DiscMod.instance().getModId() + "." + clazz.getSimpleName();
		GameRegistry.registerTileEntity(this.tileEntityClass, tileName);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		try {
			return this.tileEntityClass.newInstance();
		}
		catch (final InstantiationException ex) {
			throw new IllegalStateException(
					"Failed to create a new instance of an illegal class " + this.tileEntityClass, ex);
		}
		catch (final IllegalAccessException ex) {
			throw new IllegalStateException(
					"Failed to create a new instance of " + this.tileEntityClass + " because of a lack of permissions",
					ex);
		}
	}

	private void setTileProvider(final boolean b) {
		ReflectionHelper.setPrivateValue(Block.class, this, b, "isTileProvider");
	}

}
