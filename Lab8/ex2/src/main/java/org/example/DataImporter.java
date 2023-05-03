package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class DataImporter {
    private final ArtistDAO artistDAO;
    private final GenreDAO genreDAO;
    private final AlbumDAO albumDAO;

    public DataImporter() throws SQLException {
        artistDAO = new ArtistDAO();
        genreDAO = new GenreDAO();
        albumDAO = new AlbumDAO();
    }

    public void importDataFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext();
            String[] record;
            while ((record = reader.readNext()) != null) {
                int number = Integer.parseInt(record[0].trim());
                int year = Integer.parseInt(record[1].trim());
                String album = record[2].trim();
                String artist = record[3].trim();
                String genre = record[4].trim();
                String subgenre = record[5].trim();

                Artist artistEntity = new Artist(1 ,artist);
                artistDAO.createArtist(artistEntity);

                String[] genres = genre.split(",");
                for (String genreName : genres) {
                    Genre genreEntity = new Genre(1, genreName.trim());
                    genreDAO.createGenre(genreEntity);
                }

                Album albumEntity = new Album(year, album, artistEntity.getId());
                albumDAO.createAlbum(albumEntity);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

