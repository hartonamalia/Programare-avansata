package org.example.DAO;

import org.example.DAOInterfaces.AlbumGenreDAO;
import org.example.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AlbumGenreDAOImpl implements AlbumGenreDAO {
    /**
     * Metoda verifica daca un baza de date exista deja o combinatie albumId - genreId
     * @param albumId
     * @param genreId
     * @return
     * @throws SQLException
     */
    public boolean exists(int albumId, int genreId) throws SQLException {
        String query = "SELECT 1 FROM album_genres WHERE album_id = ? AND genre_id = ?";
        try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, albumId);
            preparedStatement.setInt(2, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }

    }

    /**
     * Metoda adauga in baza de date o lista de combinatii albumId - genreId
     * @param albumId
     * @param gendreIds
     * @throws SQLException
     */
    @Override
    public void addAlbumGenres(int albumId, List<Integer> gendreIds) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO album_genres (album_id,genre_id) VALUES(?,?)"
        )){
            for(int genreId : gendreIds)
                if (!exists(albumId, genreId)) {
                    pstmt.setInt(1, albumId);
                    pstmt.setInt(2, genreId);
                    pstmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda sterge din baza de date toate combinatiile unui anumit albumId si genurile sale
     * @param albumId
     * @throws SQLException
     */
    @Override
    public void removeAlbumGenres(int albumId) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM album_genres WHERE album_id=?"
        )){
            pstmt.setInt(1,albumId);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda sterge toate inregistrarile din tabelul album_genres
     * @throws SQLException
     */
    @Override
    public void removeAllAlbumGenres() throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM album_genres"
        )){
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
