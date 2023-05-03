package org.example.Repository;
import javax.persistence.*;


import org.example.Entity.Artist;
import org.example.EntityManagerFactoryUtil;

import java.util.List;

public class ArtistRepository {
    /**
     * Metoda salveaza un obiect de tip Artist in baza de date
     * @param artist
     */
    public void create(Artist artist) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Metoda cauta in baza de date un artist cu un id specific si il returneaza
     * @param id
     * @return
     */
    public Artist findById(int id) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        Artist artist = em.find(Artist.class, id);
        em.close();
        return artist;
    }

    /**
     * Metoda returneaza toti artistii din baza de date
     * @return
     */
    public List<Artist> findAll() {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Artist> query = em.createNamedQuery("Artist.findAll", Artist.class);
        List<Artist> artists = query.getResultList();
        em.close();
        return artists;
    }
}
