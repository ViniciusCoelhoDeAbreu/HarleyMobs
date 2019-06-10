package br.com.kickpost.harleymobs.ranks.command.subcommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.kickpost.harleymobs.ranks.dao.UserDao;
import br.com.kickpost.harleymobs.ranks.factory.Rank;
import br.com.kickpost.harleymobs.ranks.loader.RanksConfigurationLoader;
import br.com.kickpost.harleymobs.ranks.mysql.RanksStorageManager;
import br.com.kickpost.harleymobs.utils.ArgumentType;
import br.com.kickpost.harleymobs.utils.ObjectUtils;

public class RankSetCommand {

	private CommandSender sender;
	private String[] arguments;

	public RankSetCommand(CommandSender sender, String[] arguments) {
		this.sender = sender;
		this.arguments = arguments;
	}

	public void execute() {
		if (arguments.length > 2) {

			if (ObjectUtils.isValidArgument(arguments[1], ArgumentType.PLAYER, sender)
					&& RanksConfigurationLoader.getRank(arguments[2]) != null) {

				Player player = Bukkit.getPlayer(arguments[1]);
				Rank rank = RanksConfigurationLoader.getRank(arguments[2]);

				UserDao.put(player, rank, 0);
				new RanksStorageManager(UserDao.get(player)).send(false);
				sender.sendMessage(
						ChatColor.GREEN + "Rank do jogador definido para " + rank.getName() + " com sucesso!");
			}
		}
	}
}
