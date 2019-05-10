package br.com.kickpost.harleymobs.stackmobs;

import br.com.kickpost.harleymobs.factory.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.*;
import org.bukkit.event.*;
import br.com.kickpost.harleymobs.stackmobs.listeners.*;
import org.bukkit.plugin.*;

public class StackMobsMain extends Main
{
    @Override
    public void initialize() {
    }
    
    @Override
    public void registerCommands() {
    }
    
    @Override
    public void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new onMobDeathListener(), (Plugin)HarleyMobs.getPlugin());
        pm.registerEvents((Listener)new onMobSpawnListener(), (Plugin)HarleyMobs.getPlugin());
    }
}
