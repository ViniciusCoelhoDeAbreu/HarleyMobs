package br.com.kickpost.harleymobs.ranks.manager;

import br.com.kickpost.harleymobs.ranks.factory.*;
import br.com.kickpost.harleymobs.ranks.loader.*;
import br.com.kickpost.harleymobs.utils.*;
import br.com.kickpost.harleymobs.ranks.hook.*;
import br.com.kickpost.harleymobs.ranks.dao.*;
import br.com.kickpost.harleymobs.ranks.mysql.*;

public class RankupManager {
	private User user;

	public RankupManager(final User user) {
		this.user = user;
	}

	public boolean canRankup() {
		return VaultHook.get(this.user.getPlayer()) >= user.getRank().getMoneyCost()
				&& this.user.getValue() >= user.getRank().getCost();
	}

	public Rank getNextRank() {
		return RanksConfigurationLoader.getRank(this.user.getRank().getPosition() + 1);
	}

	public void rankup(final Rank toRank) {
		ObjectUtils.sendToOnlinePlayers(user.getRank().getActionBarMessage()
				.replace("{jogador}", this.user.getPlayer().getName()).replace("{ranks}", toRank.getTag()));
		ObjectUtils.sendTitle(user.getRank().getBroadcastMessage().replace("{ranks}", toRank.getTag()),
				this.user.getPlayer());
		PermissionsExHook.setPermissions(this.user.getPlayer(), toRank.getPermissions());
		VaultHook.remove(user.getPlayer(), user.getRank().getMoneyCost());

		UserDao.put(this.user.getPlayer(), toRank, 0.0);
		new RanksStorageManager(UserDao.get(this.user.getPlayer())).send(false);
	}
}
