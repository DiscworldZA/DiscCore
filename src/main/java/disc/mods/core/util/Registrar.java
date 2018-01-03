package disc.mods.core.util;

import java.util.Locale;

import disc.mods.core.DiscMod;
import disc.mods.core.block.CoreBlock;
import disc.mods.core.block.IBlockRenderer;
import disc.mods.core.init.IDiscBlocks;
import disc.mods.core.init.IDiscItems;
import disc.mods.core.items.CoreItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @credit Fireball1725/firelib
 * @author Pieter.VanLill
 *
 */
public class Registrar {

	public Block registerBlock(IForgeRegistry event, Class<? extends CoreBlock> blockClass) {
		Block block = null;
		String internalName;

		try {
			block = blockClass.getConstructor().newInstance();

			internalName = ((CoreBlock) block).getName();

			if (!internalName.equals(internalName.toLowerCase(Locale.US)))
				throw new IllegalArgumentException(
						String.format("Unlocalized names need to be all lowercase! Block is %s", internalName));

			if (internalName.isEmpty())
				throw new IllegalArgumentException(
						String.format("Unlocalized names cannot be blank! Block is %s", blockClass.getCanonicalName()));

			block.setRegistryName(internalName);
			block.setUnlocalizedName(internalName);

			event.register(block);

			if (block instanceof IBlockRenderer && DiscMod.instance().proxy().getEffectiveSide() == Side.CLIENT) {
				((IBlockRenderer) block).registerBlockRenderer();
			}

			DiscMod.instance().getLogger().info(String.format("Registered block (%s) as (%s)",
					blockClass.getCanonicalName(), block.getRegistryName()));

		} catch (Exception ex) {
			DiscMod.instance().getLogger()
					.fatal(String.format("Fatal error while registering block (%s)", blockClass.getCanonicalName()));
			ex.printStackTrace();
		}

		return block;
	}

	public void registerItemBlock(IForgeRegistry event, Block block, Class<? extends ItemBlock> itemBlockClass) {
		ItemBlock itemBlock;

		try {
			itemBlock = itemBlockClass.getConstructor(Block.class).newInstance(block);
			itemBlock.setRegistryName(block.getRegistryName());

			event.register(itemBlock);

			if (block instanceof IBlockRenderer && DiscMod.instance().proxy().getEffectiveSide() == Side.CLIENT) {
				((IBlockRenderer) block).registerBlockItemRenderer();
			}

			DiscMod.instance().getLogger()
					.info(String.format("Registered block (%s)", itemBlockClass.getCanonicalName()));
		} catch (Exception ex) {
			DiscMod.instance().getLogger().fatal(
					String.format("Fatal error while registering block (%s)", itemBlockClass.getCanonicalName()));
			ex.printStackTrace();
		}
	}

	private Item registerItem(IForgeRegistry event, Class<? extends Item> itemClass) {
		Item item = null;
		String internalName = "";

		try {
			item = itemClass.getConstructor().newInstance();

			if (item instanceof CoreItem)
				internalName = ((CoreItem) item).getName();

			if (!internalName.equals(internalName.toLowerCase(Locale.US)))
				throw new IllegalArgumentException(
						String.format("Unlocalized names need to be all lowercase! Item: %s", internalName));

			if (internalName.isEmpty())
				throw new IllegalArgumentException(
						String.format("Unlocalized name cannot be blank! Item: %s", itemClass.getCanonicalName()));

			item.setRegistryName(DiscMod.instance().getModId(), internalName);
			item.setUnlocalizedName(internalName);

			event.register(item);

			if (item instanceof IItemRenderer && DiscMod.instance().proxy().getEffectiveSide() == Side.CLIENT)
				((IItemRenderer) item).registerItemRenderer();

			DiscMod.instance().getLogger().info(String.format("Registered item (%s)", itemClass.getCanonicalName()));
		} catch (Exception ex) {
			DiscMod.instance().getLogger()
					.fatal(String.format("Fatal error while registering item (%s)", itemClass.getCanonicalName()));
			ex.printStackTrace();
		}

		return item;
	}

	@SubscribeEvent
	public final void registerBlocks(RegistryEvent.Register<Block> event) {
		DiscMod.instance().getLogger().info("Trying to register Blocks");

		if (DiscMod.instance().getBlockEnum() != null)
			DiscMod.instance().getLogger().info("Registering Blocks");
		registerEnum(DiscMod.instance().getBlockEnum(), event.getRegistry());
	}

	@SubscribeEvent
	public final void registerItems(RegistryEvent.Register<Item> event) {
		DiscMod.instance().getLogger().info("Trying to register Items");
		if (DiscMod.instance().getBlockEnum() != null) {
			DiscMod.instance().getLogger().info("Registering ItemBlocks");
			registerEnum(DiscMod.instance().getBlockEnum(), event.getRegistry());
		}

		if (DiscMod.instance().getItemEnum() != null) {
			DiscMod.instance().getLogger().info("Registering Items");
			registerEnum(DiscMod.instance().getItemEnum(), event.getRegistry());
		}
	}

	private <E extends Enum<E>> void registerEnum(Class<E> enumData, IForgeRegistry event) {
		for (Enum<E> enumObject : enumData.getEnumConstants()) {
			if (event.getRegistrySuperType() == Block.class && enumObject instanceof IDiscBlocks) {
				Block block = this.registerBlock(event, ((IDiscBlocks) enumObject).getBlockClass());
				((IDiscBlocks) enumObject).setBlock(block);
			}

			if (event.getRegistrySuperType() == Item.class && enumObject instanceof IDiscBlocks) {
				this.registerItemBlock(event, ((IDiscBlocks) enumObject).getBlock(),
						((IDiscBlocks) enumObject).getItemBlockClass());
			}

			if (event.getRegistrySuperType() == Item.class && enumObject instanceof IDiscItems) {
				Item item = registerItem(event, ((IDiscItems) enumObject).getItemClass());
				((IDiscItems) enumObject).setItem(item);
			}
		}
	}
}
