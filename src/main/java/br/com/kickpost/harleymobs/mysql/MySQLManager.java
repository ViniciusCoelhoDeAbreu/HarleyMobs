package br.com.kickpost.harleymobs.mysql;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import java.sql.*;

public class MySQLManager {
	private String user;
	private String pass;
	private String host;
	private String db;
	private MySQL sql;

	public MySQLManager(final JavaPlugin jp) {
		this.user = jp.getConfig().getString("MySQL.user");
		this.pass = jp.getConfig().getString("MySQL.pass");
		this.host = jp.getConfig().getString("MySQL.host");
		this.db = jp.getConfig().getString("MySQL.db");
		this.connect();
		this.createTables();
	}

	public void connect() {
		this.sql = new MySQL(this.user, this.pass, this.host, this.db);
		Bukkit.getLogger().info("Conexao com MySQL estabelecida com sucesso!");
	}

	public MySQL getMySQLClass() {
		return this.sql;
	}

	public void createTables() {
		try {
			this.sql.getPreparedStatement(
					"CREATE TABLE IF NOT EXISTS ranks(id INT(10) NOT NULL AUTO_INCREMENT, player VARCHAR(255), rank TEXT, value DOUBLE, PRIMARY KEY(id))")
					.execute();
			this.sql.getPreparedStatement(
					"CREATE TABLE IF NOT EXISTS spawners(id INT(10) NOT NULL AUTO_INCREMENT, location VARCHAR(255), type TEXT, amount DOUBLE, owner VARCHAR(255), PRIMARY KEY(id))")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
