package org.example.DAO;

import org.example.Database;
import org.example.model.Genre;
import org.example.DAOInterfaces.GenreDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

    /**
     * Metoda adauga in baza de date un nou gen
     * @param name
     * @throws SQLException
     */
    @Override
    public void create(String name) throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "insert into genres (name) values(?)"
        )){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda cauta un gen dupa nume
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public Genre findByName(String name) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select id from genres where name='" + name + "'"
            )
        ){
            if(rs.next()){
                int id = rs.getInt(1);
                return new Genre(id,name);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Metoda cauta un gen dupa id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Genre findById(int id) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from genres where id='" + id + "'"
            )
        ){
            if(rs.next()){
                String name = rs.getString(1);
                return new Genre(id,name);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Metoda sterge un gen identificat dupa nume
     * @param name
     * @throws SQLException
     */
    @Override
    public void remove(String name) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "delete from genres where name=?"
        )){
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda actualizeaza un gen indentificat prin nume
     * @param name
     * @param update
     * @throws SQLException
     */
    @Override
    public void update(String name, String update) throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "update genres set name=? where name=?"
        )){
            pstmt.setString(1,update);
            pstmt.setString(2,name);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda returneaza toate genurile
     * @return
     * @throws SQLException
     */
    @Override
    public List<Genre> getAllGenres() throws SQLException {

        List<Genre> genres = new ArrayList<>();
        try(Connection connection = Database.getInstance().getConnection();
                Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(("SELECT * FROM genres"));

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                genres.add(new Genre(id,name));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    /**
     * Metoda sterge toate genurile din tabel
     * @throws SQLException
     */
    @Override
    public void removeAllGenres() throws SQLException {

        try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM genres"
        )){
            pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
