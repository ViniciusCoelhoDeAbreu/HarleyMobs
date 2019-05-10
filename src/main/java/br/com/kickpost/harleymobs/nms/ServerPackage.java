package br.com.kickpost.harleymobs.nms;

import org.bukkit.*;

enum ServerPackage
{
    MINECRAFT("MINECRAFT", 0, "net.minecraft.server." + getServerVersion()), 
    CRAFTBUKKIT("CRAFTBUKKIT", 1, "org.bukkit.craftbukkit." + getServerVersion());
    
    private final String path;
    
    private ServerPackage(final String s, final int n, final String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
    
    public Class<?> getClass(final String className) throws ClassNotFoundException {
        return Class.forName(String.valueOf(this.toString()) + "." + className);
    }
    
    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }
}
