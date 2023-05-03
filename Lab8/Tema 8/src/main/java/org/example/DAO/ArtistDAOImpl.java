package org.example.DAO;

import org.example.model.Artist;
import org.example.DAOInterfaces.ArtistDAO;
import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOImpl implements ArtistDAO {
    /**
     * Metoda adauga in baza de date un nou artist
     * @param name
     * @throws SQLException
     */

    @Override
    public void create(String name) throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "insert into artists (name) values(?)"
        )){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda cauta un artist dupa nume
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public Artist findByName(String name) throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT id FROM artists WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new Artist(id,name);
            }
            else {
                return null;
            }
        }
    }

    /**
     * Metoda cauta un artist dupa id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Artist findById(int id) throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
                Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from artists where id='" + id + "'")){
            if(rs.next()){
                String name = rs.getString(1);
                return new Artist(id,name);
            }else{
                return null;
            }
        }

    }

    /**
     * Metoda sterge un anumit artist identificat prin nume
     * @param name
     * @throws SQLException
     */
    @Override
    public void remove(String name) throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "delete from artists where name=?"
        )){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }
    }

    /**
     * Metoda actualizeaza un anumit artist
     * @param name
     * @param update
     * @throws SQLException
     */
    @Override
    public void update(String name,String update) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "update artists set name=? where name=?"
        )){
            pstmt.setString(1,update);
            pstmt.setString(2,name);
            pstmt.executeUpdate();
        }
    }

    /**
     * Metoda returneaza toti artistii din tabel
     * @return
     * @throws SQLException
     */
    @Override
    public List<Artist> getAllArtists() throws SQLException {

        List<Artist> artists = new ArrayList<>();
        try(Connection connection = Database.getInstance().getConnection();
                Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(("SELECT * FROM artists"));

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                artists.add(new Artist(id,name));
            }
        }
        return artists;
    }

    /**
     * Metoda sterge din baza de date toti artistii
     * @throws SQLException
     */
    @Override
    public void removeAllArtists() throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM artists"
        )){
            pstmt.executeUpdate();
        }
    }

}
