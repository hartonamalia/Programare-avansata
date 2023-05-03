package org.example;

import com.opencsv.exceptions.CsvValidationException;
import org.example.DAO.AlbumDAOImpl;
import org.example.DAO.AlbumGenreDAOImpl;
import org.example.DAO.ArtistDAOImpl;
import org.example.DAO.GenreDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

      ArtistDAOImpl artistDAO = new ArtistDAOImpl();

        AlbumDAOImpl albumDAO = new AlbumDAOImpl();

        GenreDAOImpl genreDAO = new GenreDAOImpl();
        albumDAO.removeAll();
        artistDAO.removeAllArtists();
        AlbumGenreDAOImpl albumGenreDAO = new AlbumGenreDAOImpl();
        albumGenreDAO.removeAllAlbumGenres();
        genreDAO.removeAllGenres();
        artistDAO.removeAllArtists();
        albumDAO.removeAll();
        genreDAO.removeAllGenres();

            DataImporter dataImporter = new DataImporter();
        dataImporter.importData();
        System.out.println("Data imported successfully!");
      Database.getInstance().closeDataSource();



    }
}