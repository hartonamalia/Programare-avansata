package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private final Connection connection;

    public GenreDAO() throws SQLException {
        connection = Database.getInstance().getConnection();
    }

    public void createGenre(Genre genre) {
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Genre getGenreById(int id) {
        String sql = "SELECT * FROM genres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Genre> getAllGenres() {
        String sql = "SELECT * FROM genres";
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                genres.add(new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public void updateGenre(Genre genre, int id) {
        String sql = "UPDATE genres SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGenre(int id) {
        String sql = "DELETE FROM genres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printGenres() {
        for (Genre genre : getAllGenres()) {
            System.out.println(genre.getName());
        }
    }
}
