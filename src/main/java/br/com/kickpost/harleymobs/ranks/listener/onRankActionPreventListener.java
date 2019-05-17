package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.customspawner.loader.*;
import br.com.kickpost.harleymobs.nms.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class onRankActionPreventListener implements Listener {
	@EventHandler
	public void onHit(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player
				&& MobsConfigurationLoader.getName(e.getEntity().getType()) != null) {
			final Player player = (Player) e.getDamager();
			if (!player.hasPermission("harleymobs.matar." + e.getEntity().getName().toLowerCase())) {
				e.setCancelled(true);
				ActionBar.sendActionBarMessage(player,
						ChatColor.GOLD + "[Mobs] " + ChatColor.WHITE + "Você não tem permissão para matar este mob.");
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
			}
		}
	}
}
