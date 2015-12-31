package disc.mods.core.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class PlayerUtils
{
	public static void sendMessage(EntityPlayer player, String msg)
	{
		player.addChatComponentMessage(new ChatComponentText(msg));
	}

	public static boolean isCreative(EntityPlayer player)
	{
		return player.capabilities.isCreativeMode;
	}

	public static class Inventory
	{
		public static void SetCurrentItem(EntityPlayer player, ItemStack item)
		{
			player.setCurrentItemOrArmor(0, item);
		}
	}
}
