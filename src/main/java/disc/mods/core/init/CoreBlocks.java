package disc.mods.core.init;

import disc.mods.core.block.CoreBlock;
import disc.mods.core.block.TestBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public enum CoreBlocks implements IDiscBlocks {
	TestBlock(TestBlock.class);

	private final Class<? extends CoreBlock> blockClass;
	private final Class<? extends ItemBlock> itemBlockClass;
	private Block block;

	CoreBlocks(Class<? extends CoreBlock> blockClass) {
		this(blockClass, ItemBlock.class);
	}

	CoreBlocks(Class<? extends CoreBlock> blockClass, Class<? extends ItemBlock> itemBlockClass) {
		this.blockClass = blockClass;
		this.itemBlockClass = itemBlockClass;
	}

	@Override
	public Class<? extends CoreBlock> getBlockClass() {
		return blockClass;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return itemBlockClass;
	}

	@Override
	public void setBlock(Block block) {
		this.block = block;
	}

	@Override
	public Block getBlock() {
		return this.block;
	}

}
