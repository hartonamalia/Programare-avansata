package org.example;

import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvValidationException;
import org.example.DAO.AlbumDAOImpl;
import org.example.DAO.ArtistDAOImpl;
import org.example.DAO.GenreDAOImpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class DataImporter {
    private static final String CSV_PATH ="src/main/resources/albumlist.csv";
    private ArtistDAOImpl artistDAO;
    private AlbumDAOImpl albumDAO;
    private GenreDAOImpl genreDAO;
    public DataImporter() throws SQLException {
         artistDAO = new ArtistDAOImpl();
         albumDAO = new AlbumDAOImpl();
         genreDAO = new GenreDAOImpl();
    }

    /**
     * Metoda incarca fisierul CSV, il parseaza si adauga datele in baza de date folosindu-se de DAO-uri
     */
    public void importData() {
        Map<String, Integer> genreIdMap = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_PATH))) {
            String[] headers = reader.readNext();
            String[] record;

            while ((record = reader.readNext()) != null) {
                int number = Integer.parseInt(record[0].trim());
                int year = Integer.parseInt(record[1].trim());
                String album = record[2].trim();
                String artist = record[3].trim();
                String genre = record[4].trim();
                String subgenre = record[5].trim();

                System.out.println("Processing record: " + Arrays.toString(record));

                    if(artistDAO.findByName(artist) == null)
                    artistDAO.create(artist);

                List<Integer> genresList = new ArrayList<>();
                String[] genres = genre.split(",");
                for (String genreName : genres) {
                    int genreId;
                    if (genreIdMap.containsKey(genreName)) {
                        genreId = genreIdMap.get(genreName);
                        genresList.add(genreId);
                    } else {
                        try {
                            genreDAO.create(genreName);
                            genreId = genreDAO.findByName(genreName).getId();
                            genreIdMap.put(genreName, genreId);
                            genresList.add(genreId);
                        } catch (SQLException e) {
                            System.err.println("Error processing genre '" + genreName + "'");
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    albumDAO.create(year, album, artistDAO.findByName(artist).getId(), genresList);
                } catch (SQLException e) {
                    System.err.println("Error processing album '" + album + "'");
                    e.printStackTrace();
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
