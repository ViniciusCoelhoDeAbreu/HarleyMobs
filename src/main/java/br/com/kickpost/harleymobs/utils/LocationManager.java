package br.com.kickpost.harleymobs.utils;

import org.bukkit.*;

public class LocationManager
{
    private String toUnserialize;
    private Location toSerialize;
    
    public LocationManager(final String toUnserialize) {
        this.toUnserialize = toUnserialize;
    }
    
    public Location toLocation() {
        final String[] splitted = this.toUnserialize.split(",");
        final int x = Integer.parseInt(splitted[0]);
        final int y = Integer.parseInt(splitted[1]);
        final int z = Integer.parseInt(splitted[2]);
        final World world = Bukkit.getWorld(splitted[3]);
        return new Location(world, (double)x, (double)y, (double)z);
    }
    
    public LocationManager(final Location toSerialize) {
        this.toSerialize = toSerialize;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.toSerialize.getBlockX()) + this.SEPARATOR() + this.toSerialize.getBlockY() + this.SEPARATOR() + this.toSerialize.getBlockZ() + this.SEPARATOR() + this.toSerialize.getWorld().getName();
    }
    
    private String SEPARATOR() {
        return ",";
    }
}
