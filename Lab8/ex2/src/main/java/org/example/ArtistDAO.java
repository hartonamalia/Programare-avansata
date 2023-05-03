package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    private final Connection connection;

    public ArtistDAO() throws SQLException {
        connection = Database.getInstance().getConnection();
    }

    public void createArtist(Artist artist) {
        String sql = "INSERT INTO artists (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Artist getArtistById(int id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Artist> getAllArtists() {
        String sql = "SELECT * FROM artists";
        List<Artist> artists = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                artists.add(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    public void updateArtist(Artist artist, int id) {
        String sql = "UPDATE artists SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteArtist(int id) {
        String sql = "DELETE FROM artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printArtists() {
        for (Artist artist : getAllArtists()) {
            System.out.println(artist.getName());
        }
    }
}
