package br.com.kickpost.harleymobs.ranks.mysql;

import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import br.com.kickpost.harleymobs.ranks.dao.*;
import br.com.kickpost.harleymobs.*;
import org.bukkit.plugin.*;
import java.sql.*;
import br.com.kickpost.harleymobs.ranks.loader.*;
import br.com.kickpost.harleymobs.ranks.factory.*;

public class RanksStorageManager {

	private User user;
	private Player player;

	public RanksStorageManager(final User user) {
		this.user = user;
	}

	public RanksStorageManager(final Player player) {
		this.player = player;
	}

	public void send(final boolean remove) {
		new BukkitRunnable() {
			public void run() {
				if (contains())
					update();
				else
					insert();

				if (remove)
					UserDao.remove(RanksStorageManager.this.user.getPlayer());

			}
		}.runTaskAsynchronously((Plugin) HarleyMobs.getPlugin());
	}

	public void insert() {
		try {
			final PreparedStatement ps = HarleyMobs.getMySQL()
					.getPreparedStatement("INSERT IGNORE INTO ranks(player,rank,value) VALUES(?, ?,? )");
			ps.setString(1, this.user.getPlayer().getUniqueId().toString());
			ps.setString(2, this.user.getRank().getName());
			ps.setDouble(3, this.user.getValue());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			final PreparedStatement ps = HarleyMobs.getMySQL()
					.getPreparedStatement("UPDATE ranks SET rank=?, value=? WHERE player=?");
			ps.setString(1, this.user.getRank().getName());
			ps.setDouble(2, this.user.getValue());
			ps.setString(3, this.user.getPlayer().getUniqueId().toString());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean contains() {
		boolean value = false;
		try {
			final PreparedStatement ps = HarleyMobs.getMySQL()
					.getPreparedStatement("SELECT * FROM ranks WHERE player=?");
			ps.setString(1, this.user.getPlayer().getUniqueId().toString());
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				value = true;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void load() {
		new BukkitRunnable() {
			public void run() {
				try {
					final PreparedStatement ps = HarleyMobs.getMySQL()
							.getPreparedStatement("SELECT * FROM ranks WHERE player=?");
					ps.setString(1, RanksStorageManager.this.player.getUniqueId().toString());
					final ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						final Rank rank = RanksConfigurationLoader.getRank(rs.getString("rank"));
						final double value = rs.getDouble("value");
						UserDao.put(player, rank, value);
						return;
					}
					UserDao.put(player, RanksConfigurationLoader.getRank(1), 0.0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.runTaskAsynchronously((Plugin) HarleyMobs.getPlugin());
	}
}
