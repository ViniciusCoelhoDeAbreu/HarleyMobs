package br.com.kickpost.harleymobs.stackmobs.listeners;

import org.bukkit.event.entity.*;
import br.com.kickpost.harleymobs.stackmobs.manager.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.stackspawner.dao.*;
import br.com.kickpost.harleymobs.stackmobs.customevent.*;
import org.bukkit.block.*;
import br.com.kickpost.harleymobs.stackspawner.factory.*;
import org.bukkit.event.*;

public class onMobDeathListener implements Listener
{
    @EventHandler
    public void onDeath(final EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player && new EntityManager((Entity)e.getEntity()).isValidEntity()) {
            final Entity entity = (Entity)e.getEntity();
            final EntityManager entityManager = new EntityManager(entity);
            final CreatureSpawner spawner = new EntityManager(entity).getFromSpawner();
            final Spawner CSpawner = SpawnerDao.get(spawner.getLocation());
            if (entityManager.getAmount() > CSpawner.getAmount()) {
                final Entity newEntity = entity.getWorld().spawnEntity(entity.getLocation().clone(), entity.getType());
                final EntityManager newEntityManager = new EntityManager(newEntity);
                new EntityCustomDeathListener(e.getEntity().getKiller(), entity, entityManager.getAmount() - CSpawner.getAmount());
                newEntityManager.setAmount(entityManager.getAmount() - CSpawner.getAmount(), spawner.getLocation());
            }
            else {
                new EntityCustomDeathListener(e.getEntity().getKiller(), entity, entityManager.getAmount());
                entity.remove();
            }
        }
    }
}
