package org.example.DAO;

import org.example.*;
import org.example.DAOInterfaces.AlbumDAO;
import org.example.DAOInterfaces.AlbumGenreDAO;
import org.example.model.Album;
import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImpl implements AlbumDAO {
    /**
     * Metoda verifica daca un album exista deja
     * @param title
     * @return
     * @throws SQLException
     */
    public boolean albumExists(String title) throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "SELECT 1 FROM albums WHERE title = ?")) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metoda returneaza id-ul unui album cautandu-l dupa titlu
     * @param title
     * @return
     */
    public Integer findAlbumIdByTitle(String title) {
        Integer albumId = null;
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "SELECT id FROM albums WHERE title = ?")) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                albumId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumId;
    }

    /**
     * Metoda adauga un nou album in baza de date
     * @param releaseYear
     * @param title
     * @param artistId
     * @param genresIds
     * @throws SQLException
     */
    @Override
    public void create(int releaseYear, String title, int artistId, List<Integer> genresIds) throws SQLException {
        if (!albumExists(title)) {
            try (Connection connection = Database.getInstance().getConnection();
                    PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO albums (release_year,title,artist) VALUES (?,?,?)")) {
                pstmt.setInt(1, releaseYear);
                pstmt.setString(2, title);
                pstmt.setInt(3, artistId);
                pstmt.executeUpdate();
                System.out.println(title);
                //ResultSet rs = pstmt.executeQuery();
                if (albumExists(title)) {
                    AlbumDAOImpl albumDAO = new AlbumDAOImpl();
                    int albumId = findAlbumIdByTitle(title);
                    AlbumGenreDAO albumGenreDAO = new AlbumGenreDAOImpl();
                    albumGenreDAO.addAlbumGenres(albumId, genresIds);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The album already exists!");
        }
    }

    /**
     * Metoda cauta un album dupa titlu
     * @param title
     * @return
     * @throws SQLException
     */
    @Override
    public Album findByTitle(String title) throws SQLException {
        Album album = null;
        List<Genre> genres = new ArrayList<>();


        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "SELECT a.id, a.release_year, a.title, a.artist, g.id as genre, g.name as genre_name FROM albums a JOIN album_genres ag ON a.id = ag.album_id JOIN genres g ON ag.genre_id = g.id WHERE a.title = ?"
        )) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (album == null) {
                    int id = rs.getInt("id");
                    int release_year = rs.getInt("release_year");
                    int artistId = rs.getInt("artist");
                    album = new Album(id, release_year, title, artistId);
                }
                int genreId = rs.getInt("genre");
                String genreName = rs.getString("genre_name");
                genres.add(new Genre(genreId, genreName));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    /**
     * Metoda cauta un album dupa id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Album findById(int id) throws SQLException {
        Album album = null;
        List<Genre> genres = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "SELECT a.id, a.release_year, a.title, a.artist, g.id as genre, g.name as genre_name FROM albums a JOIN album_genres ag ON a.id = ag.album_id JOIN genres g ON ag.genre_id = g.id WHERE a.id = ?"
        )) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (album == null) {
                    int release_year = rs.getInt("release_year");
                    String title = rs.getString("title");
                    int artistId = rs.getInt("artist");
                    album = new Album(id, release_year, title, artistId);
                }
                int genreId = rs.getInt("genre");
                String genreName = rs.getString("genre_name");
                genres.add(new Genre(genreId, genreName));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    /**
     * Metoda sterge din baza de date un anumit album indentificat prin id
     * @param id
     * @throws SQLException
     */
    @Override
    public void remove(int id) throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement removeAlbumGenres = connection.prepareStatement(
                     "DELETE FROM album_genres WHERE album_id=?"
             );
             PreparedStatement removeAlbum = connection.prepareStatement(
                     "DELETE FROM albums WHERE id=?"
             )) {

            removeAlbumGenres.setInt(1, id);
            removeAlbumGenres.executeUpdate();

            removeAlbum.setInt(1, id);
            removeAlbum.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Metoda actualizeaza in baza de date un anumit album identificat prin id
     * @param id
     * @param releaseYear
     * @param title
     * @param artistId
     * @param genres
     * @throws SQLException
     */
    @Override
    public void update(int id, int releaseYear, String title, int artistId, List<Integer> genres) throws SQLException {
        AlbumGenreDAO albumGenreDAO = new AlbumGenreDAOImpl();
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement updateAlbum = connection.prepareStatement("UPDATE albums SET release_year = ?, title = ?, artist = ? WHERE id = ?");
             PreparedStatement deleteAlbumGenre = connection.prepareStatement("DELETE FROM album_genres WHERE album_id = ?")) {

            updateAlbum.setInt(1, releaseYear);
            updateAlbum.setString(2, title);
            updateAlbum.setInt(3, artistId);
            updateAlbum.setInt(4, id);
            updateAlbum.executeUpdate();

            deleteAlbumGenre.setInt(1, id);
            deleteAlbumGenre.executeUpdate();

            albumGenreDAO.addAlbumGenres(id, genres);

        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Metoda sterge din baza de date toate albumele
     * @throws SQLException
     */
    @Override
    public void removeAll() throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement removeAlbumGenres = connection.prepareStatement("DELETE FROM album_genres");
             PreparedStatement removeAlbums = connection.prepareStatement("DELETE FROM albums")) {

            removeAlbumGenres.executeUpdate();
            removeAlbums.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }
}

