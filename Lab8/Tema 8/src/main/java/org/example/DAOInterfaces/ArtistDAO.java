package org.example.DAOInterfaces;

import org.example.model.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDAO {
    void create(String name) throws SQLException;
    Artist findByName(String name) throws SQLException;
    Artist findById(int id) throws SQLException;
    void remove(String name) throws SQLException;
    void update(String name,String update) throws SQLException;
    List<Artist> getAllArtists() throws SQLException;
    void removeAllArtists() throws SQLException;
}
