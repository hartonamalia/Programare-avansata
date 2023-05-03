package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/PA";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";
    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    private static HikariDataSource dataSource;

    private static Database instance;

    private Database() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.setDriverClassName(DRIVER_CLASS);

        dataSource = new HikariDataSource(config);
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
