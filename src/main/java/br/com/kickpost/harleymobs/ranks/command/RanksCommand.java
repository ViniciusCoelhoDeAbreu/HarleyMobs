package br.com.kickpost.harleymobs.ranks.command;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.ranks.inventory.*;

public class RanksCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("ranks") && sender instanceof Player) {
            final Player player = (Player)sender;
            final RanksMainInventoryViewer ranksInventory = new RanksMainInventoryViewer(player);
            ranksInventory.load();
            ranksInventory.open();
        }
        return false;
    }
}
