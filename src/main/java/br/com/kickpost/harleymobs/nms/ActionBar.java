package br.com.kickpost.harleymobs.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

	private static Class<?> CRAFTPLAYERCLASS, PACKET_PLAYER_CHAT_CLASS, ICHATCOMP, CHATMESSAGE, PACKET_CLASS;

	private static Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR, CHATMESSAGE_CONSTRUCTOR;

	private static final String SERVER_VERSION;

	static {
		String name = Bukkit.getServer().getClass().getName();
		name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
		name = name.substring(0, name.indexOf("."));
		SERVER_VERSION = name;

		try {
			CRAFTPLAYERCLASS = Class.forName("org.bukkit.craftbukkit." + SERVER_VERSION + ".entity.CraftPlayer");
			PACKET_PLAYER_CHAT_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".PacketPlayOutChat");
			PACKET_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".Packet");
			ICHATCOMP = Class.forName("net.minecraft.server." + SERVER_VERSION + ".IChatBaseComponent");
			PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP, byte.class);

			CHATMESSAGE = Class.forName("net.minecraft.server." + SERVER_VERSION + ".ChatMessage");

			CHATMESSAGE_CONSTRUCTOR = CHATMESSAGE.getDeclaredConstructor(String.class, Object[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendActionBarMessage(Player player, String message) {
		try {
			Object icb = CHATMESSAGE_CONSTRUCTOR.newInstance(message, new Object[0]);

			Object packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, (byte) 2);

			Object craftplayerInst = CRAFTPLAYERCLASS.cast(player);

			Method methodHandle = CRAFTPLAYERCLASS.getMethod("getHandle");

			Object methodhHandle = methodHandle.invoke(craftplayerInst);

			Object playerConnection = methodhHandle.getClass().getField("playerConnection").get(methodhHandle);

			playerConnection.getClass().getMethod("sendPacket", PACKET_CLASS).invoke(playerConnection, packet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
