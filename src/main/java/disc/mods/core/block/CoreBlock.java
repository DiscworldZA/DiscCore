package disc.mods.core.block;

import disc.mods.core.DiscMod;
import disc.mods.core.ref.References;
import disc.mods.core.util.StringUtil;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/***
 * @reference Fireball1725/firelib
 * @author Pieter.VanLill
 *
 */
public abstract class CoreBlock extends Block implements IBlockRenderer {
	public String Name;
	public String resourcePath;
	public static final PropertyDirection FACING = PropertyDirection.create(References.NBT.Direction,
			EnumFacing.Plane.HORIZONTAL);

	public CoreBlock(Material materialIn, String resourcePath) {
		super(materialIn);
		this.resourcePath = resourcePath;

		if (this.canRotate()) {
			this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}
	}

	public CoreBlock(String Name) {
		this(Material.ROCK, Name);
	}

	@Override
	public CoreBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public String getUnlocalizedName() {
		String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

		return String.format("tile.%s.%s", DiscMod.instance.getModId(), blockName);
	}

	private String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}

	public String getName() {
		return Name;
	}

	public void setName(String internalName) {
		this.Name = internalName;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public boolean canRotate() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		if (canRotate()) {
			EnumFacing playerFacing = placer.getHorizontalFacing().getOpposite();
			if (placer.isSneaking())
				playerFacing = placer.getHorizontalFacing();

			worldIn.setBlockState(pos, state.withProperty(FACING, playerFacing), 2);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (canRotate())
			return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));

		return super.getStateFromMeta(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (canRotate())
			return (state.getValue(FACING)).getHorizontalIndex();

		return super.getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (canRotate()) {
			return new BlockStateContainer(this, FACING);
		}
		return super.createBlockState();
	}

	// Rendering
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockRenderer() {
		final String resourcePath = String.format("%s:%s", DiscMod.instance.getModId(), this.resourcePath);

		ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
			@SideOnly(Side.CLIENT)
			@Override
			@MethodsReturnNonnullByDefault
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
			}
		});
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockItemRenderer() {
		final String resourcePath = String.format("%s:%s", DiscMod.instance.getModId(), this.resourcePath);

		NonNullList<ItemStack> subBlocks = NonNullList.create();
		getSubBlocks(null, subBlocks);

		for (ItemStack itemStack : subBlocks) {
			IBlockState blockState = this.getStateFromMeta(itemStack.getItemDamage());
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), itemStack.getItemDamage(),
					new ModelResourceLocation(resourcePath, StringUtil.getPropertyString(blockState.getProperties())));
		}
	}

}
