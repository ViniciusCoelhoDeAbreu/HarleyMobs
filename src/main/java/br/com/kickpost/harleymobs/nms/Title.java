package br.com.kickpost.harleymobs.nms;

import org.json.simple.*;
import org.bukkit.entity.*;
import com.google.common.base.*;
import java.util.logging.*;
import org.bukkit.*;

public class Title {
	private JSONObject title;
	private JSONObject subtitle;
	private int fadeIn;
	private int fadeOut;
	private int stay;
	public static boolean DEBUG;

	public Title(final String title, final String subtitle, final int fadeIn, final int stay, final int fadeOut) {
		this.title = convert(title);
		this.subtitle = convert(subtitle);
		this.fadeIn = fadeIn;
		this.fadeOut = fadeOut;
		this.stay = stay;
	}

	public Title(final JSONObject title, final JSONObject subtitle, final int fadeIn, final int fadeOut,
			final int stay) {
		this.title = title;
		this.subtitle = subtitle;
		this.fadeIn = fadeIn;
		this.fadeOut = fadeOut;
		this.stay = stay;
	}

	public void send(final Player player) {
		Preconditions.checkNotNull((Object) player);
		try {
			final Object handle = player.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]).invoke(player,
					new Object[0]);
			final Object connection = handle.getClass().getField("playerConnection").get(handle);
			final Class<?> playPacket = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle");
			final Class<?> genericPacket = ServerPackage.MINECRAFT.getClass("Packet");
			final Class<?> chatComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent");
			final Class<?> serializer = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer");
			final Class<?> action = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle$EnumTitleAction");
			final Object timesPacket = playPacket.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE)
					.newInstance(this.fadeIn, this.stay, this.fadeOut);
			connection.getClass().getMethod("sendPacket", genericPacket).invoke(connection, timesPacket);
			if (this.title != null && !this.title.isEmpty()) {
				final Object titleComponent = serializer.getMethod("a", String.class).invoke(null,
						this.title.toString());
				final Object titlePacket = playPacket.getConstructor(action, chatComponent)
						.newInstance(action.getField("TITLE").get(null), titleComponent);
				connection.getClass().getMethod("sendPacket", genericPacket).invoke(connection, titlePacket);
			}
			if (this.subtitle != null && !this.subtitle.isEmpty()) {
				final Object subtitleComponent = serializer.getMethod("a", String.class).invoke(null,
						this.subtitle.toString());
				final Object subtitlePacket = playPacket.getConstructor(action, chatComponent)
						.newInstance(action.getField("SUBTITLE").get(null), subtitleComponent);
				connection.getClass().getMethod("sendPacket", genericPacket).invoke(connection, subtitlePacket);
			}
		} catch (Exception e) {
			if (Title.DEBUG) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Failed to send title.", e);
			} else {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						"Failed to send title to {0}. Is TextAPI updated?", player.getName());
			}
		}
	}

	public void sendToAll() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			this.send(player);
		}
	}

	public JSONObject getTitle() {
		return this.title;
	}

	public JSONObject getSubtitle() {
		return this.subtitle;
	}

	public int getFadeIn() {
		return this.fadeIn;
	}

	public int getFadeOut() {
		return this.fadeOut;
	}

	public int getStay() {
		return this.stay;
	}

	public void setTitle(final String title) {
		this.title = convert(title);
	}

	public void setSubtitle(final String subtitle) {
		this.subtitle = convert(subtitle);
	}

	public void setTitle(final JSONObject title) {
		this.title = title;
	}

	public void setSubtitle(final JSONObject subtitle) {
		this.subtitle = subtitle;
	}

	public void setFadeIn(final int fadeIn) {
		this.fadeIn = fadeIn;
	}

	public void setFadeOut(final int fadeOut) {
		this.fadeOut = fadeOut;
	}

	public void setStay(final int stay) {
		this.stay = stay;
	}

	@SuppressWarnings("unchecked")
	static JSONObject convert(final String text) {
		final JSONObject json = new JSONObject();
		json.put("text", text);
		return json;
	}
}
