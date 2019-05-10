package br.com.kickpost.harleymobs.ranks.api;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;

import br.com.kickpost.harleymobs.ranks.dao.UserDao;
import br.com.kickpost.harleymobs.ranks.factory.Rank;
import br.com.kickpost.harleymobs.ranks.hook.VaultHook;
import br.com.kickpost.harleymobs.ranks.manager.RankupManager;

public class RanksAPI {

	private Player player;

	public RanksAPI(Player player) {
		this.player = player;
	}

	public Rank getCurrentRank() {
		return UserDao.get(player).getRank();
	}

	public Rank getNextRank() {
		return new RankupManager(UserDao.get(player)).getNextRank();
	}

	public double getCurrentValue() {
		return UserDao.get(player).getValue();
	}

	public double getNecessaryValue() {
		return getNextRank().getCost();
	}

	public double getProgress() {
		Rank nextRank = getNextRank();

		double moneyProgress = (VaultHook.get(player) / nextRank.getMoneyCost());
		double costProgress = (UserDao.get(player).getValue() / nextRank.getCost());

		double progress = BigDecimal.valueOf((moneyProgress * costProgress) * 100).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();

		return progress > 100 ? 100 : progress;
	}
}
