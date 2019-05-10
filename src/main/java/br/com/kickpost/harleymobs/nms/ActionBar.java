package br.com.kickpost.harleymobs.nms;

import org.json.simple.*;
import com.google.common.base.*;
import org.bukkit.entity.*;
import java.util.logging.*;
import org.bukkit.*;

public class ActionBar {
	public static boolean DEBUG;
	private JSONObject json;

	public ActionBar(final String text) {
		Preconditions.checkNotNull((Object) text);
		this.json = Title.convert(text);
	}

	public ActionBar(final JSONObject json) {
		Preconditions.checkNotNull((Object) json);
		Preconditions.checkArgument(!json.isEmpty());
		this.json = json;
	}

	public void send(final Player player) {
		Preconditions.checkNotNull((Object) player);
		try {
			final Object handle = player.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]).invoke(player,
					new Object[0]);
			final Object connection = handle.getClass().getField("playerConnection").get(handle);
			final Object component = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer")
					.getMethod("a", String.class).invoke(null, this.json.toString());
			final Object packet = ServerPackage.MINECRAFT.getClass("PacketPlayOutChat")
					.getConstructor(ServerPackage.MINECRAFT.getClass("IChatBaseComponent"), Byte.TYPE)
					.newInstance(component, 2);
			connection.getClass().getMethod("sendPacket", ServerPackage.MINECRAFT.getClass("Packet")).invoke(connection,
					packet);
		} catch (Exception e) {
			if (ActionBar.DEBUG) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send action bar message.", e);
			} else {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						"Failed to send action bar message to {0}. Is TextAPI updated?", player.getName());
			}
		}
	}

	public void sendToAll() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			this.send(player);
		}
	}

	public void setText(final String text) {
		Preconditions.checkNotNull((Object) text);
		this.json = Title.convert(text);
	}

	public void setJsonText(final JSONObject json) {
		Preconditions.checkNotNull((Object) json);
		Preconditions.checkArgument(!json.isEmpty());
		this.json = json;
	}

	@Deprecated
	public static void send(final Player player, final String message) {
		new ActionBar(message).send(player);
	}

	@Deprecated
	public static void sendToAll(final String message) {
		new ActionBar(message).sendToAll();
	}
}
