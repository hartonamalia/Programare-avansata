package org.example;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/PA";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;


    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    /**
     * Metoda creeaza o conexiune cu baza de date
     */
    private static void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda inchide o conexiune
     */
    public static void closeConnection() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            connection.close();
        }
    }
}
