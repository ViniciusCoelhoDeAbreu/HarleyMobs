package br.com.kickpost.harleymobs.utils;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class ItemManager
{
    private int id;
    private byte data;
    
    public ItemManager(final String id) {
        this.id = this.getID(id);
        this.data = this.getData(id);
    }
    
    public int getId() {
        return this.id;
    }
    
    public byte getData() {
        return this.data;
    }
    
    private int getID(final String id) {
        if (id.contains(":")) {
            return Integer.parseInt(id.split(":")[0]);
        }
        return Integer.parseInt(id);
    }
    
    private byte getData(final String id) {
        if (id.contains(":")) {
            return Byte.parseByte(id.split(":")[1]);
        }
        return 0;
    }
    
    @SuppressWarnings("deprecation")
	public ItemStack build() {
        return new ItemStack(Material.getMaterial(this.getId()), 1, (short)0, this.getData());
    }
}
