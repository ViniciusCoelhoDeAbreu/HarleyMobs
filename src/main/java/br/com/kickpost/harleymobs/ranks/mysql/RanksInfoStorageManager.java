package br.com.kickpost.harleymobs.ranks.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.ranks.factory.Rank;
import br.com.kickpost.harleymobs.ranks.factory.RankPlayer;
import br.com.kickpost.harleymobs.ranks.loader.RanksConfigurationLoader;

public class RanksInfoStorageManager {

	public RanksInfoStorageManager() {

	}

	public void insert() {
		Collection<Rank> ranks = RanksConfigurationLoader.getRanks();
		try {
			PreparedStatement ps = HarleyMobs.getMySQL()
					.getPreparedStatement("INSERT IGNORE INTO ranks_info(rank, value) VALUES(?,?)");
			for (Rank rank : ranks) {
				ps.setString(1, rank.getName());
				ps.setInt(2, rank.getPosition());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public HashMap<Integer, RankPlayer> getPlayers() {
		HashMap<Integer, RankPlayer> player_by_position = new HashMap<>();
		try {

			PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement(
					"SELECT r.player, r.rank,r.value FROM ranks r JOIN ranks_info ri ON ri.rank = r.rank ORDER BY ri.value DESC LIMIT 10 ");

			ResultSet rs = ps.executeQuery();
			int currentPosition = 1;

			while (rs.next()) {
				player_by_position.put(currentPosition,
						new RankPlayer(Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("r.player"))),
								RanksConfigurationLoader.getRank(rs.getString("r.rank"))));
				currentPosition++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return player_by_position;
	}
}
