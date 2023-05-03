package org.example.DAOInterfaces;

import org.example.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {
    void create(String name) throws SQLException;
    Genre findByName(String name) throws SQLException;
    Genre findById(int id) throws SQLException;
    void remove(String name) throws SQLException;
    void update(String name,String update) throws SQLException;
    List<Genre> getAllGenres() throws SQLException;
    void removeAllGenres() throws SQLException;
}
