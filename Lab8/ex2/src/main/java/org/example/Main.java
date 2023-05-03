package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataImporter dataImporter;
        try {
            dataImporter = new DataImporter();
            dataImporter.importDataFromCSV("src/main/resources/albumlist.csv");
            System.out.println("Dataset imported to database!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
