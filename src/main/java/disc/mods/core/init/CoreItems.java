package disc.mods.core.init;

import disc.mods.core.items.CoreItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoreItems {

	public static void init() {
	}

	protected static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof CoreItem) {
			((CoreItem) item).registerItemModel();
		}

		return item;
	}

}
