package disc.mods.core.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public abstract class BlocksHandler
{	
	public static void Register(Block block, String Name)
	{
		GameRegistry.registerBlock(block, Name);
	}
}
