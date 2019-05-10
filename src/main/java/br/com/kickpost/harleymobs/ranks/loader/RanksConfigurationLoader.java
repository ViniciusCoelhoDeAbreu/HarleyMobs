package br.com.kickpost.harleymobs.ranks.loader;

import com.google.common.collect.*;
import br.com.kickpost.harleymobs.*;
import java.util.concurrent.*;
import br.com.kickpost.harleymobs.ranks.type.*;
import br.com.kickpost.harleymobs.ranks.factory.*;
import org.bukkit.configuration.*;
import java.util.*;
import java.util.stream.*;

public class RanksConfigurationLoader {
	protected static final HashMap<String, Rank> RANK_BY_NAME = Maps.newHashMap();

	public RanksConfigurationLoader() {
		this.setup();
	}

	private void setup() {
		final ConfigurationSection SECTION = HarleyMobs.getPlugin().getConfig().getConfigurationSection("Ranks");
		final Set<String> KEYS = (Set<String>) SECTION.getKeys(false);

		final ConcurrentHashMap<CategoryType, Page> CURRENT_INDEX_BY_CATEGORY = new ConcurrentHashMap<CategoryType, Page>();
		Stream.of(CategoryType.values()).forEach(c -> CURRENT_INDEX_BY_CATEGORY.put(c, new Page(0)));

		for (final String name : KEYS) {
			final String key = name;
			final double moneyCost = SECTION.getDouble(String.valueOf(key) + ".CustoMoney");
			final double cost = SECTION.getDouble(String.valueOf(key) + ".Custo");
			final String itemCategoryName = SECTION.getString(String.valueOf(key) + ".Evoluir");
			final int position = SECTION.getInt(String.valueOf(key) + ".Posicao");
			final String actionBarMessage = SECTION.getString(String.valueOf(key) + ".ActionBarMessage");
			final String broadcastMessage = SECTION.getString(String.valueOf(key) + ".BroadcastMessage");
			final String tag = SECTION.getString(String.valueOf(key) + ".Tag");
			final int itemSlot = SECTION.getInt(String.valueOf(key) + ".ItemSlot");
			final int ItemAmount = SECTION.getInt(String.valueOf(key) + ".ItemQuantia");
			final List<String> itemLore = (List<String>) SECTION.getStringList(String.valueOf(key) + ".ItemLore");
			final String id = SECTION.getString(String.valueOf(key) + ".Id");
			final CategoryType category = CategoryType
					.valueOf(SECTION.getString(String.valueOf(key) + ".Categoria").toUpperCase());
			final List<String> permissions = (List<String>) SECTION.getStringList(String.valueOf(key) + ".Permissoes");
			if (CURRENT_INDEX_BY_CATEGORY.get(category).getCurrentIndex() == 9) {
				CURRENT_INDEX_BY_CATEGORY.get(category).setPage(CURRENT_INDEX_BY_CATEGORY.get(category).getPage() + 1);
				CURRENT_INDEX_BY_CATEGORY.get(category).setCurrentIndex(0);
			}
			RanksConfigurationLoader.RANK_BY_NAME.put(name,
					new Rank(name, moneyCost, cost, new Category(itemCategoryName, category), position,
							actionBarMessage, broadcastMessage, tag, new Item(id, itemSlot, ItemAmount, itemLore),
							permissions, CURRENT_INDEX_BY_CATEGORY.get(category).getPage()));
			CURRENT_INDEX_BY_CATEGORY.get(category)
					.setCurrentIndex(CURRENT_INDEX_BY_CATEGORY.get(category).getCurrentIndex() + 1);
		}
	}

	public static final Rank getRank(final String name) {
		return RanksConfigurationLoader.RANK_BY_NAME.get(name);
	}

	public static final Rank getRank(final int position) {
		return RanksConfigurationLoader.RANK_BY_NAME.values().stream().filter(r -> r.getPosition() == position)
				.findFirst().orElse(null);
	}

	public static final Rank getNextRank(final Rank rank) {
		return RanksConfigurationLoader.RANK_BY_NAME.values().stream()
				.filter(r -> r.getPosition() == rank.getPosition() + 1).findFirst().orElse(null);
	}

	public static final Set<Rank> getRanks(final CategoryType categoryType, final int page) {
		return RanksConfigurationLoader.RANK_BY_NAME.values().stream()
				.filter(r -> r.getCategory().getCategoryType().equals(categoryType)).filter(p -> p.getPage() == page)
				.collect(Collectors.toSet());
	}
}
