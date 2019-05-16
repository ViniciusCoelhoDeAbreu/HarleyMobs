package br.com.kickpost.harleymobs.stackmobs.listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import br.com.kickpost.harleymobs.customspawner.factory.Drop;
import br.com.kickpost.harleymobs.stackmobs.customevent.EntityCustomDeathListener;
import br.com.kickpost.harleymobs.stackmobs.manager.EntityManager;
import br.com.kickpost.harleymobs.stackspawner.dao.SpawnerDao;
import br.com.kickpost.harleymobs.stackspawner.factory.Spawner;
import br.com.kickpost.harleymobs.utils.ObjectUtils;

public class onMobDeathListener implements Listener {
	@EventHandler
	public void onDeath(final EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player && new EntityManager((Entity) e.getEntity()).isValidEntity()) {
			final Entity entity = (Entity) e.getEntity();
			Player player = e.getEntity().getKiller();
			final EntityManager entityManager = new EntityManager(entity);
			final CreatureSpawner spawner = new EntityManager(entity).getFromSpawner();
			final Spawner CSpawner = SpawnerDao.get(spawner.getLocation());

			e.getDrops().clear();

			if (entityManager.getAmount() > CSpawner.getAmount()) {
				final Entity newEntity = entity.getWorld().spawnEntity(entity.getLocation().clone(), entity.getType());
				final EntityManager newEntityManager = new EntityManager(newEntity);
				dropItems(ObjectUtils.getDrops(player, entity.getType(), (int) CSpawner.getAmount()),
						entity.getLocation());
				new EntityCustomDeathListener(e.getEntity().getKiller(), entity,
						entityManager.getAmount() - CSpawner.getAmount());
				newEntityManager.setAmount(entityManager.getAmount() - CSpawner.getAmount(), spawner.getLocation());
			} else {
				new EntityCustomDeathListener(e.getEntity().getKiller(), entity, entityManager.getAmount());
				dropItems(ObjectUtils.getDrops(player, entity.getType(), (int) entityManager.getAmount()),
						entity.getLocation());
				entity.remove();
			}
		}
	}

	private void dropItems(List<Drop> drops, Location location) {
		if (drops != null)
			drops.forEach(d -> location.getWorld().dropItemNaturally(location, d.build()));
	}
}
