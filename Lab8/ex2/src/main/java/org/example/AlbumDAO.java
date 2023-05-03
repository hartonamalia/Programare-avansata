package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    private final Connection connection;

    public AlbumDAO() throws SQLException {
        connection = Database.getInstance().getConnection();
    }

    public void createAlbum(Album album) {
        String sql = "INSERT INTO albums (release_year, title, artist_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getReleaseYear());
            statement.setString(2, album.getTitle());
            statement.setInt(3, album.getArtistId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Album getAlbumById(int id) {
        String sql = "SELECT * FROM albums WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Album(
                        resultSet.getInt("id"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("title"),
                        resultSet.getInt("artist_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> getAllAlbums() {
        String sql = "SELECT * FROM albums";
        List<Album> albums = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                albums.add(new Album(
                        resultSet.getInt("id"),
                        resultSet.getInt("release_year"),
                        resultSet.getString("title"),
                        resultSet.getInt("artist_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public void updateAlbum(Album album, int id) {
        String sql = "UPDATE albums SET release_year = ?, title = ?, artist_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, album.getReleaseYear());
            statement.setString(2, album.getTitle());
            statement.setInt(3, album.getArtistId());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAlbum(int id) {
        String sql = "DELETE FROM albums WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAlbums(){
        for(Album album : getAllAlbums())
        {
            System.out.println(album);
        }
    }
}
