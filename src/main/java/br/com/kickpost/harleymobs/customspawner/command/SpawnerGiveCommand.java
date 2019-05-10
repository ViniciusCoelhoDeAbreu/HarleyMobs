package br.com.kickpost.harleymobs.customspawner.command;

import org.bukkit.command.*;
import br.com.kickpost.harleymobs.utils.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.customspawner.loader.*;
import org.bukkit.inventory.*;
import br.com.kickpost.harleymobs.customspawner.builder.*;
import org.bukkit.entity.*;

public class SpawnerGiveCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("mgive")
				&& ObjectUtils.hasArguments(args, 3, ChatColor.RED + "Use: /mgive <player> <mob> <quantidade> ", sender)
				&& ObjectUtils.isValidArgument(args[0], ArgumentType.PLAYER, sender)
				&& ObjectUtils.isValidArgument(args[1], ArgumentType.MOB, sender)
				&& ObjectUtils.isValidArgument(args[2], ArgumentType.DOUBLE, sender)) {
			
			final Player player = Bukkit.getPlayer(args[0]);
			final EntityType entityType = MobsConfigurationLoader.get(args[1]);
			final double amount = Double.parseDouble(args[2]);
			player.getInventory().addItem(new ItemStack[] { new SpawnerItemBuilder(entityType, amount).build() });
			sender.sendMessage(ChatColor.GREEN + "Spawner dado ao jogador com sucesso!");
		}
		return false;
	}
}
