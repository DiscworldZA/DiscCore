package disc.mods.core.init;

import disc.mods.core.items.TestItem;
import net.minecraft.item.Item;

public enum CoreItems implements IDiscItems {
<<<<<<< HEAD
	;
=======
	TestItem(TestItem.class);

>>>>>>> e8a5c0b9100de7f0f393563f17f4139939f12540
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
