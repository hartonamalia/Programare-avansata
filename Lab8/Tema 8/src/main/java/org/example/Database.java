package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private final HikariDataSource dataSource;
    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    private Database() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/PA");
        config.setUsername("postgres");
        config.setPassword("1234");
//        config.setConnectionTimeout(300000);
//        config.setConnectionTimeout(120000);
//        config.setLeakDetectionThreshold(300000);
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

    // Add a method to close the data source when the application shuts down
    public void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
