package br.com.kickpost.harleymobs.ranks.command;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import br.com.kickpost.harleymobs.ranks.command.subcommand.RankSetCommand;
import br.com.kickpost.harleymobs.ranks.inventory.*;

public class RanksCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("ranks") && sender instanceof Player) {
			final Player player = (Player) sender;

			if (args.length == 0) {
				final RanksMainInventoryViewer ranksInventory = new RanksMainInventoryViewer(player);
				ranksInventory.load();
				ranksInventory.open();
			} else if (args[0].equalsIgnoreCase("top")) {
				RanksTopInventoryViewer ranksTopInventory = new RanksTopInventoryViewer(player);
				ranksTopInventory.load();
				ranksTopInventory.open();
			} else if (args[0].equalsIgnoreCase("set") && player.isOp()) {
				new RankSetCommand(sender, args).execute();
			}
		}
		return false;
	}
}
