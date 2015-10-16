package disc.mods.core.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class PlayerUtils
{
	public static void sendMessage(EntityPlayer player, String msg)
	{
		player.addChatComponentMessage(new ChatComponentText(msg));
	}
}
