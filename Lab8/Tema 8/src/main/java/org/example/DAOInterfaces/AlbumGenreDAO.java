package org.example.DAOInterfaces;

import java.sql.SQLException;
import java.util.List;

public interface AlbumGenreDAO {
    void addAlbumGenres(int albumId, List<Integer> gendreIds) throws SQLException;
    void removeAlbumGenres(int albumId) throws SQLException;
    void removeAllAlbumGenres() throws SQLException;
}
