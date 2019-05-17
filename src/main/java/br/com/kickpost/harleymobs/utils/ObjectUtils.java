package br.com.kickpost.harleymobs.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.com.kickpost.harleymobs.customspawner.factory.CustomEntity;
import br.com.kickpost.harleymobs.customspawner.factory.Drop;
import br.com.kickpost.harleymobs.customspawner.loader.MobsConfigurationLoader;
import br.com.kickpost.harleymobs.nms.ActionBar;
import br.com.kickpost.harleymobs.nms.Title;

public class ObjectUtils {
	private static final NumberFormat formatter;

	static {
		formatter = new DecimalFormat("###,###,###");
	}

	public static String format(final String string) {
		final String typeName = string.replace("_", " ");
		final StringJoiner newName = new StringJoiner(" ");
		Stream.of(typeName.split(" ")).forEach(
				r -> newName.add(String.valueOf(r.substring(0, 1).toUpperCase()) + r.substring(1).toLowerCase()));
		return newName.toString();
	}

	public static List<Drop> getDrops(Player player, EntityType entityType, int entityAmount) {
		if (MobsConfigurationLoader.get(entityType) != null) {

			CustomEntity customEntity = MobsConfigurationLoader.get(entityType);
			List<Drop> drops = new ArrayList<Drop>();

			int lootingMultiplier = (player.getItemInHand() != null && player.getItemInHand().hasItemMeta()
					&& player.getItemInHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_MOBS))
							? player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS)
							: 1;

			customEntity.getDrops().stream().forEach(d -> {
				Drop drop = d;
				drop.setAmount(entityAmount * lootingMultiplier);
				drops.add(drop);
			});
			return drops;
		}
		return null;
	}

	public static void removeItem(final Player player, final ItemStack item) {
		if (item.getAmount() > 1) {
			item.setAmount(item.getAmount() - 1);
		} else {
			player.getInventory().removeItem(new ItemStack[] { item });
		}
	}

	public static void sendToOnlinePlayers(final String message) {
		Bukkit.getOnlinePlayers().forEach(p -> ActionBar.sendActionBarMessage(p, message));
	}

	public static void sendTitle(final String message, final Player player) {
		final String title = message.contains("<nl>") ? message.split("<nl>")[0] : message;
		final String subTitle = message.contains("<nl>") ? message.split("<nl>")[1] : new String();
		new Title(title, subTitle, 10, 10, 10).send(player);
	}

	public static boolean hasArguments(final String[] args, final int minArgs, final String errorMessage,
			final CommandSender sender) {
		if (args.length < minArgs) {
			sender.sendMessage(errorMessage);
			return false;
		}
		return true;
	}

	public static boolean isValidArgument(final String argument, final ArgumentType argumentType,
			final CommandSender sender) {
		switch (argumentType) {
		case INTEGER: {
			try {
				Integer.parseInt(argument);
				return true;
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "O n\u00famero informado é inválido.");
				return false;
			}
		}
		case DOUBLE: {
			try {
				Double.parseDouble(argument);
				return true;
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "O número informado é inválido.");
				return false;
			}
		}
		case PLAYER: {
			if (Bukkit.getPlayer(argument) == null) {
				sender.sendMessage(ChatColor.RED + "O jogador informado não existe.");
				return false;
			}
			return true;
		}
		case MOB: {
			if (MobsConfigurationLoader.get(argument) == null) {
				sender.sendMessage(ChatColor.RED + "O Mob informado não existe.");
				return false;
			}
			return true;
		}
		default: {
			return false;
		}
		}
	}

	public static String getFormatter(final double value) {
		if (value < 1000.0) {
			return ObjectUtils.formatter.format(value);
		}
		if (value < 1000000.0) {
			return String.valueOf(String.format("%.2f", value / 1000.0).replace(",", ".")) + "K";
		}
		if (value < 1.0E9) {
			return String.valueOf(String.format("%.2f", value / 1000000.0).replace(",", ".")) + "M";
		}
		if (value < 1.0E12) {
			return String.valueOf(String.format("%.2f", value / 1.0E9).replace(",", ".")) + "B";
		}
		if (value < 1.0E15) {
			return String.valueOf(String.format("%.2f", value / 1.0E12).replace(",", ".")) + "T";
		}
		if (value < 1.0E18) {
			return String.valueOf(String.format("%.2f", value / 1.0E15).replace(",", ".")) + "Q";
		}
		if (value < 1.0E21) {
			return String.valueOf(String.format("%.2f", value / 1.0E18).replace(",", ".")) + "Qi";
		}
		if (value < 1.0E24) {
			return String.valueOf(String.format("%.2f", value / 1.0E21).replace(",", ".")) + "S";
		}
		if (value < 1.0E27) {
			return String.valueOf(String.format("%.2f", value / 1.0E24).replace(",", ".")) + "Se";
		}
		if (value < 1.0E30) {
			return String.valueOf(String.format("%.2f", value / 1.0E27).replace(",", ".")) + "O";
		}
		if (value < 1.0E33) {
			return String.valueOf(String.format("%.2f", value / 1.0E30).replace(",", ".")) + "N";
		}
		if (value < 1.0E36) {
			return String.valueOf(String.format("%.2f", value / 1.0E33).replace(",", ".")) + "D";
		}
		if (value < 1.0E39) {
			return String.valueOf(String.format("%.2f", value / 1.0E36).replace(",", ".")) + "Ud";
		}
		if (value < 1.0E41) {
			return String.valueOf(String.format("%.2f", value / 1.0E39).replace(",", ".")) + "Dd";
		}
		if (value < 1.0E44) {
			return String.valueOf(String.format("%.2f", value / 1.0E41).replace(",", ".")) + "Td";
		}
		if (value < 1.0E47) {
			return String.valueOf(String.format("%.2f", value / 1.0E44).replace(",", ".")) + "Qd";
		}
		if (value < 1.0E50) {
			return String.valueOf(String.format("%.2f", value / 1.0E47).replace(",", ".")) + "Qid";
		}
		if (value < 1.0E53) {
			return String.valueOf(String.format("%.2f", value / 1.0E50).replace(",", ".")) + "Sd";
		}
		return ObjectUtils.formatter.format(value).replace(",", ".");
	}
}
