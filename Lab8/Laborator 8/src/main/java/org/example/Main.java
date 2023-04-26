package org.example;

import org.example.test.ArtistDAOTest;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws SQLException {
        ArtistDAO artists = new ArtistDAO();
        String name1 = "Justin Bieber";
        String name2 = "The Weeknd";
        int idToSearch = 1;
        try{
            artists.create(name1);
       }catch (SQLException e)
        {
            System.err.println("Error inserting artist '" + name1 + "' into database: " + e.getMessage());
       }
       try{
            artists.create(name2);
       }catch (SQLException e)
       {
            System.err.println("Error inserting artist '" + name2 + "' into database: " + e.getMessage());
       }
        try{
            Integer id = artists.findByName(name2);
            if(id != null){
                System.out.println("The artist " + name2 + " exists with id: " + id);
            } else{
                System.out.println("Artistul " + name2 + " does not exists!");
            }
        } catch (SQLException e){
            System.err.println("Error finding artist by name: " + e.getMessage());
        }
        try{
            String searchedName = artists.findById(idToSearch);
            if(searchedName != null){
                System.out.println("The artist with id: " + idToSearch + " exists and has name "  + searchedName);
            } else{
                System.out.println("The artist with id: " + idToSearch + " does not exist");
            }
        } catch (SQLException e){
            System.err.println("Error finding artist by name: " + e.getMessage());
        }
    }

}