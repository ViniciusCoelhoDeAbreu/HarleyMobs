package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.event.player.*;
import br.com.kickpost.harleymobs.ranks.mysql.*;
import org.bukkit.event.*;

public class onPlayerJoinListener implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        new RanksStorageManager(e.getPlayer()).load();
    }
}
