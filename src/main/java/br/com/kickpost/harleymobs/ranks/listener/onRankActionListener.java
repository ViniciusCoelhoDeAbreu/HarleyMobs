package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import br.com.kickpost.harleymobs.customspawner.loader.MobsConfigurationLoader;
import br.com.kickpost.harleymobs.nms.ActionBar;
import br.com.kickpost.harleymobs.ranks.dao.UserDao;
import br.com.kickpost.harleymobs.ranks.factory.User;
import br.com.kickpost.harleymobs.ranks.loader.PlantationConfigurationLoader;
import br.com.kickpost.harleymobs.ranks.type.CategoryType;
import br.com.kickpost.harleymobs.stackmobs.customevent.EntityCustomDeathListener;
import br.com.kickpost.harleymobs.utils.ObjectUtils;

public class onRankActionListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlantationBreak(final BlockBreakEvent e) {

		if (e.getBlock() != null && PlantationConfigurationLoader.getSize(e.getBlock().getType()) != -1
				&& e.getBlock().getData() == PlantationConfigurationLoader.getSize(e.getBlock().getType())) {
			final Player player = e.getPlayer();
			final User user = UserDao.get(player);
			if (user.getRank().getCategory().getCategoryType().equals(CategoryType.FAZENDEIRO)
					&& user.getRank().getCategory().getItemName().equalsIgnoreCase(e.getBlock().getType().name())) {
				user.setValue(user.getValue() + 1.0);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMobKill(final EntityCustomDeathListener e) {
		final User user = UserDao.get(e.getPlayer());
		if (user.getRank().getCategory().getCategoryType().equals(CategoryType.ASSASSINO)
				&& user.getRank().getCategory().getItemName().equalsIgnoreCase(e.getEntity().getType().name())) {
			user.setValue(user.getValue() + e.getAmount());
			ActionBar.send(e.getPlayer(),
					ChatColor.GOLD + "[Mobs] " + ChatColor.WHITE + "Você matou " + ChatColor.GOLD
							+ ObjectUtils.getFormatter(e.getAmount()) + " "
							+ MobsConfigurationLoader.getName(e.getEntity().getType()) + ChatColor.WHITE + " e recebeu "
							+ ChatColor.GOLD + ObjectUtils.getFormatter(e.getAmount()) + " Cabeças.");
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
		}
	}

	@EventHandler
	public void onBreakBlock(final BlockBreakEvent e) {
		if (e.getBlock() == null)
			return;

		final Block block = e.getBlock();
		final Player player = e.getPlayer();
		final User user = UserDao.get(player);
		if (user.getRank().getCategory().getCategoryType().equals(CategoryType.MINERADOR)
				&& user.getRank().getCategory().getItemName().equalsIgnoreCase(block.getType().name())) {
			user.setValue(user.getValue() + 1.0);
		}
	}
}
