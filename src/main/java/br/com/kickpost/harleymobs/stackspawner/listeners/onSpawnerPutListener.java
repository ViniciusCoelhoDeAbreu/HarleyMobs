package br.com.kickpost.harleymobs.stackspawner.listeners;

import org.bukkit.event.block.*;
import br.com.kickpost.harleymobs.customspawner.manager.*;
import br.com.kickpost.harleymobs.stackspawner.manager.*;
import org.bukkit.block.*;
import br.com.kickpost.harleymobs.stackspawner.factory.*;
import br.com.kickpost.harleymobs.stackspawner.dao.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.stackspawner.mysql.*;
import br.com.kickpost.harleymobs.utils.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class onSpawnerPutListener implements Listener {
	@EventHandler
	public void onSpawnerPut(final BlockPlaceEvent e) {
		if (new SpawnerItemManager(e.getItemInHand()).isSpawner()
				&& e.getPlayer().getWorld().getName().equalsIgnoreCase("plotworld")) {
			Player player = e.getPlayer();
			br.com.kickpost.harleymobs.customspawner.factory.Spawner spawner = new SpawnerItemManager(e.getItemInHand())
					.getSpawner();
			SpawnerManager spawnerManager = new SpawnerManager(e.getBlock().getLocation());

			if (spawnerManager.getMostProximitySpawner(spawner.getEntityType(), player) == null) {
				final CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlock().getState();
				creatureSpawner.setSpawnedType(spawner.getEntityType());
				final Spawner CSpawner = new Spawner(spawner.getEntityType(), spawner.getAmount(), player.getUniqueId(),
						creatureSpawner.getLocation().clone());
				SpawnerDao.put(e.getBlock().getLocation().clone(), CSpawner);
				player.sendMessage(ChatColor.GREEN + "Spawner colocado com sucesso!");
				spawnerManager.putHologram(CSpawner, true);
				new SpawnerStorageManager().send(CSpawner);
			} else {
				e.setBuild(false);
				e.setCancelled(true);
				final Spawner mostProximitySpawner = spawnerManager.getMostProximitySpawner(spawner.getEntityType(),
						player);
				player.sendMessage(ChatColor.GREEN + "Spawner stackado com outros "
						+ ObjectUtils.getFormatter(mostProximitySpawner.getAmount()) + " spawners.");
				mostProximitySpawner.setAmount(spawner.getAmount() + mostProximitySpawner.getAmount());
				ObjectUtils.removeItem(player, e.getItemInHand());
				spawnerManager.putHologram(mostProximitySpawner, false);
				new SpawnerStorageManager().send(mostProximitySpawner);
			}
		}
	}
}
