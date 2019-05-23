package br.com.kickpost.harleymobs.mysql;

import com.zaxxer.hikari.*;
import java.sql.*;

public class MySQL {
	private String user;
	private String pass;
	private String db;
	private String host;
	private HikariDataSource datasource;
	private Connection connection;

	public MySQL(final String user, final String pass, final String host, final String db) {
		this.user = user;
		this.pass = pass;
		this.db = db;
		this.host = host;
		this.datasource = new HikariDataSource();
		this.connect();
	}

	public void close() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (Exception ex) {
		}
	}

	public void connect() {
		try {
			this.datasource.setPoolName("HarleyMobsSQLPool");
			this.datasource.setMaximumPoolSize(25);
			this.datasource.setMaxLifetime(1800000L);
			this.datasource.setJdbcUrl("jdbc:mysql://" + this.host + ":3306" + "/" + this.db + "?autoReconnect=true");
			this.datasource.setUsername(this.user);
			this.datasource.setPassword(this.pass);
			this.datasource.addDataSourceProperty("useSSL", "false");
			this.datasource.addDataSourceProperty("characterEncoding", "utf8");
			this.datasource.addDataSourceProperty("encoding", "UTF-8");
			this.datasource.addDataSourceProperty("useUnicode", "true");
			this.datasource.addDataSourceProperty("rewriteBatchedStatements", "true");
			this.datasource.addDataSourceProperty("jdbcCompliantTruncation", "false");
			this.datasource.addDataSourceProperty("cachePrepStmts", "true");
			this.datasource.addDataSourceProperty("prepStmtCacheSize", "275");
			this.datasource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			this.connection = this.datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(final String query) {
		try {
			return this.connection.prepareStatement(query);
		} catch (SQLException e) {
			connect();
			getPreparedStatement(query);
			return null;
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}
