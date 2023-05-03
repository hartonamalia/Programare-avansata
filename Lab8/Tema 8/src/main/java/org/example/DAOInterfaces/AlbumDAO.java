package org.example.DAOInterfaces;

import org.example.model.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDAO {
    void create(int releaseYear,String title, int artistId, List<Integer> genresIds) throws SQLException;
    Album findByTitle(String title) throws SQLException;
    Album findById(int id) throws SQLException;
    void remove(int id) throws SQLException;
    void update(int id,int releaseYear,String title,int artistId,List<Integer> genres) throws SQLException;
    void removeAll() throws  SQLException;
}
