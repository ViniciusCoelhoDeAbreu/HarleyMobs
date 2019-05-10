package br.com.kickpost.harleymobs.stackspawner.mysql;

import br.com.kickpost.harleymobs.stackspawner.factory.*;
import org.bukkit.scheduler.*;
import br.com.kickpost.harleymobs.*;
import org.bukkit.plugin.*;
import br.com.kickpost.harleymobs.utils.*;
import java.sql.*;
import java.util.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.stackspawner.dao.*;
import br.com.kickpost.harleymobs.stackspawner.manager.*;
import org.bukkit.*;

public class SpawnerStorageManager
{
    public void send(final Spawner spawner) {
        new BukkitRunnable() {
            public void run() {
                if (SpawnerStorageManager.this.contains(spawner)) {
                    SpawnerStorageManager.this.update(spawner);
                }
                else {
                    SpawnerStorageManager.this.insert(spawner);
                }
            }
        }.runTaskAsynchronously((Plugin)HarleyMobs.getPlugin());
    }
    
    public void update(final Spawner spawner) {
        try {
            final PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement("UPDATE spawners SET amount=? WHERE location=? AND owner=?");
            ps.setDouble(1, spawner.getAmount());
            ps.setString(2, new LocationManager(spawner.getCreatureSpawner().getLocation()).toString());
            ps.setString(3, spawner.getOwner().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insert(final Spawner spawner) {
        try {
            final PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement("INSERT IGNORE INTO spawners(location,type,amount,owner) VALUES(?,?,?,?)");
            ps.setString(1, new LocationManager(spawner.getCreatureSpawner().getLocation()).toString());
            ps.setString(2, spawner.getEntityType().name());
            ps.setDouble(3, spawner.getAmount());
            ps.setString(4, spawner.getOwner().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean contains(final Spawner spawner) {
        boolean value = false;
        try {
            final PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement("SELECT * FROM spawners WHERE location=?");
            ps.setString(1, new LocationManager(spawner.getCreatureSpawner().getLocation()).toString());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                value = true;
            }
            rs.close();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    
    public void remove(final Spawner spawner) {
        try {
            final PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement("DELETE FROM spawners WHERE location=?");
            ps.setString(1, new LocationManager(spawner.getCreatureSpawner().getLocation()).toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
        new BukkitRunnable() {
            public void run() {
                try {
                    final PreparedStatement ps = HarleyMobs.getMySQL().getPreparedStatement("SELECT * FROM spawners");
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        final Location location = new LocationManager(rs.getString("location")).toLocation();
                        final double amount = rs.getDouble("amount");
                        final UUID owner = UUID.fromString(rs.getString("owner"));
                        final String type = rs.getString("type");
                        final Spawner spawner = new Spawner(EntityType.valueOf(type), amount, owner, location);
                        SpawnerDao.put(location, spawner);
                        new SpawnerManager(spawner.getLocation()).putHologram(spawner, true);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously((Plugin)HarleyMobs.getPlugin());
    }
}
