package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SlimeSplitEvent;

public class onEntityPreventListener implements Listener {

	@EventHandler
	public void onSlimeSplit(SlimeSplitEvent e) {
		e.setCancelled(true);
	}
}
