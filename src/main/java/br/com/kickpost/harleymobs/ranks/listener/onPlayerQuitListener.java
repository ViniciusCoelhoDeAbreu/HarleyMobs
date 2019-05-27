package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.event.player.*;
import br.com.kickpost.harleymobs.ranks.dao.*;
import br.com.kickpost.harleymobs.ranks.mysql.*;
import br.com.kickpost.harleymobs.stackspawner.dao.DelayDao;

import org.bukkit.event.*;

public class onPlayerQuitListener implements Listener {
	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		new RanksStorageManager(UserDao.get(e.getPlayer())).send(true);
		DelayDao.remove(e.getPlayer());
	}
}
