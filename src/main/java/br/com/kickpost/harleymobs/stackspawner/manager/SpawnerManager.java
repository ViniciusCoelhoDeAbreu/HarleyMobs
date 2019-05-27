package br.com.kickpost.harleymobs.stackspawner.manager;

import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.stackspawner.factory.*;
import br.com.kickpost.harleymobs.stackspawner.dao.*;
import org.bukkit.block.*;
import br.com.kickpost.harleymobs.*;
import com.gmail.filoghost.holographicdisplays.api.*;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;
import br.com.kickpost.harleymobs.customspawner.loader.*;
import br.com.kickpost.harleymobs.utils.*;
import org.bukkit.*;

public class SpawnerManager {
	private Location blockLocation;

	public SpawnerManager(final Location blockLocation) {
		this.blockLocation = blockLocation;
	}

	public Spawner getMostProximitySpawner(final EntityType type, final Player player) {
		for (int x = this.blockLocation.getBlockX() - 5; x <= this.blockLocation.getBlockX() + 5; ++x) {
			for (int y = this.blockLocation.getBlockY() - 5; y <= this.blockLocation.getBlockY() + 5; ++y) {
				for (int z = this.blockLocation.getBlockZ() - 5; z <= this.blockLocation.getBlockZ() + 5; ++z) {
					final Block block = this.blockLocation.getWorld().getBlockAt(x, y, z);
					if (block.getType().equals((Object) Material.MOB_SPAWNER)
							&& !this.blockLocation.equals((Object) block.getLocation())
							&& SpawnerDao.contains(block.getLocation())
							&& SpawnerDao.get(block.getLocation()).getEntityType().equals((Object) type)
							&& SpawnerDao.get(block.getLocation()).getOwner().equals(player.getUniqueId())) {
						final Spawner spawner = SpawnerDao.get(block.getLocation());
						return spawner;
					}
				}
			}
		}
		return null;
	}

	public void removeHologram(final Spawner spawner) {
		final Hologram hologram = HologramsAPI.getHolograms((Plugin) HarleyMobs.getPlugin()).stream()
				.filter(h -> h.getWorld().getName().equals(spawner.getLocation().getWorld().getName()))
				.filter(h -> h.getLocation()
						.distance(spawner.getLocation().getBlock().getLocation().clone().add(0.5, 3.0, 0.8)) < 1.0)
				.findFirst().get();
		hologram.delete();
	}

	public void putHologram(final Spawner spawner, final boolean firstTime) {
		new BukkitRunnable() {
			public void run() {
				if (firstTime) {
					final Hologram hologram = HologramsAPI.createHologram((Plugin) HarleyMobs.getPlugin(),
							spawner.getLocation().getBlock().getLocation().clone().add(0.5, 3.0, 0.8));
					hologram.appendTextLine(String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD.toString()
							+ "GERADOR DE MONSTROS");
					hologram.appendTextLine(ChatColor.WHITE + "Tipo: " + ChatColor.GOLD
							+ MobsConfigurationLoader.getName(spawner.getEntityType()));
					hologram.appendTextLine(ChatColor.WHITE + "Quantidade: " + ChatColor.GOLD
							+ ObjectUtils.getFormatter(spawner.getAmount()));
					hologram.appendTextLine("");
					hologram.appendTextLine(ChatColor.WHITE + "Dono: " + ChatColor.GOLD
							+ Bukkit.getOfflinePlayer(spawner.getOwner()).getName());
					hologram.appendTextLine("");
				} else {
					try {
						final Hologram hologram = HologramsAPI.getHolograms((Plugin) HarleyMobs.getPlugin()).stream()
								.filter(h -> h.getWorld().getName().equals(spawner.getLocation().getWorld().getName()))
								.filter(h -> h.getLocation().distance(spawner.getLocation().getBlock().getLocation()
										.clone().add(0.5, 3.0, 0.8)) < 1.0)
								.findFirst().get();
						hologram.removeLine(2);
						hologram.insertTextLine(2, ChatColor.WHITE + "Quantidade: " + ChatColor.GOLD
								+ ObjectUtils.getFormatter(spawner.getAmount()));
					} catch (Exception e) {
						SpawnerManager.this.putHologram(spawner, true);
					}
				}
			}
		}.runTask(HarleyMobs.getPlugin());
	}
}
