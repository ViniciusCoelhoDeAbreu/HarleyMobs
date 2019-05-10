package br.com.kickpost.harleymobs.stackmobs.listeners;

import org.bukkit.event.entity.*;
import br.com.kickpost.harleymobs.stackspawner.dao.*;
import br.com.kickpost.harleymobs.stackmobs.manager.*;
import br.com.kickpost.harleymobs.stackspawner.factory.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class onMobSpawnListener implements Listener
{
    @EventHandler
    public void onSpawn(final SpawnerSpawnEvent e) {
        if (SpawnerDao.contains(e.getSpawner().getLocation())) {
            final Spawner spawner = SpawnerDao.get(e.getSpawner().getLocation());
            final EntityManager entityManager = new EntityManager(e.getEntity());
            if (entityManager.getMostProximityEntity() == null) {
                entityManager.setAmount(spawner.getAmount(), e.getSpawner().getLocation());
            }
            else {
                e.setCancelled(true);
                final Entity mostProximityEntity = entityManager.getMostProximityEntity();
                final EntityManager mostProximityEntityManager = new EntityManager(mostProximityEntity);
                mostProximityEntityManager.setAmount(mostProximityEntityManager.getAmount() + spawner.getAmount(), e.getSpawner().getLocation());
            }
        }
    }
}
