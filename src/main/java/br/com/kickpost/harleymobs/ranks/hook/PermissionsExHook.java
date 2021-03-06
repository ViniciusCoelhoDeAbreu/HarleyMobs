package br.com.kickpost.harleymobs.ranks.hook;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.*;
import java.util.*;
import ru.tehkode.permissions.bukkit.*;

public class PermissionsExHook {
	public static void setPermissions(final Player player, final List<String> permissions) {
		permissions.forEach(perm -> PermissionsEx.getUser(player).addPermission(perm));
	}

	@SuppressWarnings("deprecation")
	public static String getPrefix(OfflinePlayer player) {
		try {
			return ChatColor.translateAlternateColorCodes('&',
					PermissionsEx.getUser(player.getName()).getGroups()[0].getPrefix());
		} catch (NullPointerException e) {
			return "";
		}
	}
}
