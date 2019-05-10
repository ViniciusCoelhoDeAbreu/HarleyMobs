package br.com.kickpost.harleymobs.ranks.command;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.ranks.dao.*;
import br.com.kickpost.harleymobs.ranks.manager.*;
import br.com.kickpost.harleymobs.ranks.loader.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.utils.*;
import br.com.kickpost.harleymobs.ranks.factory.*;

public class RankupCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("rankup") && sender instanceof Player) {
			final Player player = (Player) sender;
			final User user = UserDao.get(player);
			final RankupManager rankupManager = new RankupManager(user);
			if (rankupManager.getNextRank() == null) {
				player.sendMessage(ChatColor.RED + "Você já alcançou o rank máximo!");
				return true;
			}
			if (rankupManager.canRankup()) {
				player.sendMessage(ChatColor.GOLD + "[Ranks] " + ChatColor.WHITE + "Você evoluiu para "
						+ ChatColor.YELLOW + rankupManager.getNextRank().getName() + ".");
				rankupManager.rankup(RanksConfigurationLoader.getNextRank(user.getRank()));
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
			} else {
				player.sendMessage(ChatColor.GOLD + "[Ranks] " + ChatColor.WHITE + "Você precisa de " + ChatColor.GOLD
						+ rankupManager.getNextRank().getCategory().toString(rankupManager.getNextRank())
						+ ChatColor.WHITE + " e" + ChatColor.GOLD + " "
						+ ObjectUtils.getFormatter(rankupManager.getNextRank().getMoneyCost()) + " Money"
						+ ChatColor.WHITE + " para evoluir.");
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
			}
		}
		return false;
	}
}
