package disc.mods.core.init;

import disc.mods.core.block.CoreBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public interface IDiscBlocks {
	Class<? extends CoreBlock> getBlockClass();

	Class<? extends ItemBlock> getItemBlockClass();

	void setBlock(Block block);

	Block getBlock();
}
