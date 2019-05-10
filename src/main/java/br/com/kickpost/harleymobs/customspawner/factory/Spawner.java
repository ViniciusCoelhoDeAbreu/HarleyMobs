package br.com.kickpost.harleymobs.customspawner.factory;

import org.bukkit.entity.*;

public class Spawner
{
    private EntityType entityType;
    private double amount;
    
    public Spawner(final EntityType entityType, final double amount) {
        this.entityType = entityType;
        this.amount = amount;
    }
    
    public EntityType getEntityType() {
        return this.entityType;
    }
    
    public double getAmount() {
        return this.amount;
    }
}
