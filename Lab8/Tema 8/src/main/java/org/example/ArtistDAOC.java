package org.example;

import java.sql.*;

public class ArtistDAOC {
    /**
     * Metoda creeaza si insereaza o linie noua in baza de date folosindu-se de numele dat de utilizator
     * @param name
     * @throws SQLException
     */
    public void create(String name) throws SQLException{
        Connection con = Database.getInstance().getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)")){
            System.out.println(name);
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        }
    }

    /**
     * Metoda cauta in baza de date daca exista o linie in care unul din coloane contine numele name si returneaza id-ul artistului daca exista
     * @param name
     * @return
     * @throws SQLException
     */
    public Integer findByName(String name) throws SQLException{
        Connection con = Database.getInstance().getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select id from artists where name='" + name + "'")){
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    /**
     * Metoda cauta in baza de date daca exista o linie in care unul din coloane contine id-ul id si returneaza numele artistului daca exista
     * @param id
     * @return
     * @throws SQLException
     */
    public String findById(int id) throws SQLException{
        Connection con = Database.getInstance().getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from artists where id='" + id + "'")){
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
