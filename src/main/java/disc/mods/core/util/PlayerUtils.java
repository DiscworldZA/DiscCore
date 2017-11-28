package disc.mods.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

public class PlayerUtils
{
    public static void sendMessage(EntityPlayer player, String msg)
    {
        player.sendMessage(new TextComponentString(msg));
    }

    public static void setPresistedData(EntityPlayer player, String key, String value)
    {
        NBTTagCompound presistedData = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
        if (presistedData == null)
            presistedData = new NBTTagCompound();
        presistedData.setString(key, value);
        player.getEntityData().setTag(player.PERSISTED_NBT_TAG, presistedData);
    }

    public static String getPresistedData(EntityPlayer player, String key)
    {
        return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).getString(key);
    }

    public static boolean hasPresistedData(EntityPlayer player, String key)
    {
        return player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG).hasKey(key);
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
