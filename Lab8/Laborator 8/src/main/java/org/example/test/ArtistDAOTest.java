package org.example.test;

import org.example.ArtistDAO;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ArtistDAOTest {

    /**
     * Metoda testeaza daca metoda de creare functioneaza corect, dupa creare caut in baza de date numele artistului creat si verific daca id-ul lui e acelasi cu cel pe care il asteptam, daca da atunci artistul a fost adaugat corespunzator
     * @throws SQLException
     */
    @Test
    public void createTest() throws SQLException {
        ArtistDAO artists = new ArtistDAO();
        String name = "Puya";
        artists.create(name);
        Integer expectedId = 3;
        Integer resultId = artists.findByName(name);
        assertEquals(expectedId,resultId);
    }
    /**
     * Metoda testeaza daca metoda de cautare prin nume functioneaza corect, caut in baza de date un nume ce stiu ca are id-ul 2 si verific daca metoda returneaza acelasi lucru
     * @throws SQLException
     */
    @Test
    public void findByNameTest() throws SQLException {
        ArtistDAO artists = new ArtistDAO();
        String name2 = "The Weeknd";
        Integer expectedId = 2;
        Integer resultId = artists.findByName(name2);
        assertEquals(expectedId,resultId);
    }
    /**
     * Metoda testeaza daca metoda de cautare prin id functioneaza corect, caut in baza de date un id ce stiu ca are ca nume Michael Jackson si verific daca metoda returneaza acelasi lucru
     * @throws SQLException
     */
    @Test
    public void findByIdTest() throws SQLException {
        ArtistDAO artists = new ArtistDAO();
        String expectedName = "The Weeknd";
        int id = 2;
        String resultName = artists.findById(id);
        assertEquals(expectedName,resultName);
    }
}
