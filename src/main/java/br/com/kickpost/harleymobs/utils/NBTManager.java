package br.com.kickpost.harleymobs.utils;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class NBTManager
{
    private ItemStack item;
    
    public NBTManager(final ItemStack item) {
        this.item = item;
    }
    
    public String getTag(final String key) {
        try {
            return CraftItemStack.asNMSCopy(this.item).getTag().getString(key);
        }
        catch (Exception e) {
            return new String();
        }
    }
    
    public ItemStack addTag(final String key, final String value) {
        final net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(this.item);
        final NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
        compound.setString(key, value);
        nmsStack.setTag(compound);
        final ItemStack item = CraftItemStack.asBukkitCopy(nmsStack);
        item.setAmount(item.getAmount());
        return item;
    }
}
