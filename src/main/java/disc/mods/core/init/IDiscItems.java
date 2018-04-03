package disc.mods.core.init;

import net.minecraft.item.Item;

public interface IDiscItems {
	Class<? extends Item> getItemClass();

	Item getItem();

	void setItem(Item item);
}
