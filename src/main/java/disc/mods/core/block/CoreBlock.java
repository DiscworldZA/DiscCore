package disc.mods.core.block;

import disc.mods.core.DiscMod;
import disc.mods.core.client.gui.inventory.CoreGui;
import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.util.StringUtil;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
	public int guiId = -1;

	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public CoreBlock(String name, Material materialIn, String resourcePath) {
		super(materialIn);
		this.resourcePath = resourcePath;
		this.setUnlocalizedName(DiscMod.instance().getModId() + "." + name);
		this.Name = name;

		if (this.canRotate()) {
			this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}
	}

	public String getModId() {
		return this.getRegistryName().getResourceDomain();
	}

	public CoreBlock(String name, String resourcePath) {
		this(name, Material.ROCK, resourcePath);
	}

	@Override
	public CoreBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s.%s", this.getRegistryName().getResourceDomain(),
				this.getRegistryName().getResourcePath());
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public boolean canRotate() {
		return false;
	}

	public boolean canRotateVertically() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
	                            ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (canRotate()) {
			EnumFacing playerFacing = placer.getHorizontalFacing().getOpposite();
			if (canRotateVertically()) {
				playerFacing = EnumFacing.getDirectionFromEntityLiving(pos, placer);
			}
			if (placer.isSneaking())
				playerFacing = playerFacing.getOpposite();

			worldIn.setBlockState(pos, state.withProperty(FACING, playerFacing), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
	                                EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hasGui()) {
			OpenGui(playerIn, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (canRotate())
			return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));

		return super.getStateFromMeta(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if (canRotate())
			return (state.getValue(FACING)).getIndex();

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
		final String resourcePath = String.format("%s:%s", DiscMod.instance().getModId(), this.resourcePath);

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
		final String resourcePath = String.format("%s:%s", DiscMod.instance().getModId(), this.resourcePath);

		NonNullList<ItemStack> subBlocks = NonNullList.create();
		getSubBlocks(null, subBlocks);

		for (ItemStack itemStack : subBlocks) {
			IBlockState blockState = this.getDefaultState();
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), itemStack.getItemDamage(),
					new ModelResourceLocation(resourcePath, StringUtil.getPropertyString(blockState.getProperties())));
		}
	}

	public boolean hasGui() {
		return guiId != -1;
	}

	public void registerGUI(Class<? extends CoreGui> gui, Class<? extends CoreContainer> container) {
		guiId = DiscMod.instance().getGuiHandler().Register(gui, container);
	}

	public void OpenGui(EntityPlayer player, World world, int x, int y, int z) {
		player.openGui(DiscMod.instance(getModId()), guiId, world, x, y, z);
	}
}
