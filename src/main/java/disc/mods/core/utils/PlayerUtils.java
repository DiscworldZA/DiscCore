package disc.mods.core.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class PlayerUtils
{
	public static void sendMessage(EntityPlayer player, String msg)
	{
		player.sendMessage(new TextComponentString(msg));
	}

	public static boolean isCreative(EntityPlayer player)
	{
		return player.capabilities.isCreativeMode;
	}

	public static class Inventory
	{
		public static void SetItem(EntityPlayer player, ItemStack item, EntityEquipmentSlot slot)
		{
			player.setItemStackToSlot(slot, item);
		}
	}
}
