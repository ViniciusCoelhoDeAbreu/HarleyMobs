package br.com.kickpost.harleymobs.stackspawner.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import br.com.kickpost.harleymobs.customspawner.builder.SpawnerItemBuilder;
import br.com.kickpost.harleymobs.stackspawner.dao.DelayDao;
import br.com.kickpost.harleymobs.stackspawner.dao.SpawnerDao;
import br.com.kickpost.harleymobs.stackspawner.factory.Spawner;
import br.com.kickpost.harleymobs.stackspawner.manager.SpawnerManager;
import br.com.kickpost.harleymobs.stackspawner.mysql.SpawnerStorageManager;

public class onSpawnerBreakListener implements Listener {
	@EventHandler
	public void onBreak(final BlockBreakEvent e) {
		if (e.getBlock() != null && SpawnerDao.contains(e.getBlock().getLocation())) {
			Player player = e.getPlayer();
			Spawner spawner = SpawnerDao.get(e.getBlock().getLocation());

			e.setCancelled(true);

			if (DelayDao.has(player)) {
				player.sendMessage(
						ChatColor.RED + "Aguarde " + DelayDao.get(player) + " segundos para quebrar outro spawner.");
				return;
			}

			if (player.getItemInHand() != null && player.getItemInHand().getType() != null
					&& player.getItemInHand().getType().equals((Object) Material.DIAMOND_PICKAXE)
					&& player.getInventory().firstEmpty() != -1 && spawner.getOwner().equals(player.getUniqueId())) {

				if (player.isSneaking() || spawner.getAmount() == 1) {
					new SpawnerManager(spawner.getLocation()).removeHologram(spawner);
					SpawnerDao.remove(spawner.getLocation());
					spawner.getLocation().getBlock().setType(Material.AIR);
					player.getInventory()
							.addItem(new SpawnerItemBuilder(spawner.getEntityType(), spawner.getAmount()).build());
				} else {
					spawner.setAmount(spawner.getAmount() - 1);
					player.getInventory().addItem(new SpawnerItemBuilder(spawner.getEntityType(), 1.0).build());
					new SpawnerStorageManager().update(spawner);
					new SpawnerManager(spawner.getLocation()).putHologram(spawner, false);
				}
				DelayDao.put(player);
			}
		}
	}

	public static void main(String[] args) {
		int goal = 94;

		int value = 0;

		for (int i = goal; i > 0; i--) {
			value += goal;
		}
		System.out.println(value);
	}
}
