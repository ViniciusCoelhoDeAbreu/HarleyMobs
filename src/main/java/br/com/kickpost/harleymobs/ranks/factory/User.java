package br.com.kickpost.harleymobs.ranks.factory;

import org.bukkit.entity.*;

public class User
{
    private Player player;
    private Rank rank;
    private double value;
    
    public User(final Player player, final Rank rank, final double value) {
        this.player = player;
        this.rank = rank;
        this.value = value;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Rank getRank() {
        return this.rank;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
}
