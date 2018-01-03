package disc.mods.core.init;

import disc.mods.core.items.TestItem;
import net.minecraft.item.Item;

public enum CoreItems implements IDiscItems {
	;
	CoreItems(Class<? extends Item> itemClass) {
		this.itemClass = itemClass;
	}

	private final Class<? extends Item> itemClass;
	private Item item;

	@Override
	public Class<? extends Item> getItemClass() {
		return itemClass;
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public void setItem(Item item) {
		this.item = item;
	}

}
