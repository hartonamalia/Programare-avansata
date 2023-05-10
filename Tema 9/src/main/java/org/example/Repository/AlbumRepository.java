package org.example.Repository;

import org.example.Entity.Album;
import org.example.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumRepository extends AbstractRepository<Album> {
    //Clasa extinde clasa AbstractRepository si seteaza tipul generic al acesteia ca Album
    public AlbumRepository(){
        super(Album.class);
    }

    /**
     * Metoda findById este folosita pt a cauta un album dupa un anumit ID
     * @param id
     * @return
     */
    public Album findById(int id){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        Album album = em.find(Album.class,id);// gaseste albumul dupa id folosind metoda find
        em.close();// inchide conexiunea
        return album;// returneaza albumul dorit
    }

    /**
     * Metoda findByName este folosita pentru a cauta un album dupa un anumit titlu
     * @param title
     * @return
     */
    public List<Album> findByName(String title){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Album> query = em.createNamedQuery("Album.findByName",Album.class);//creeaza o interogare cu numele "Album.findByName"
        query.setParameter("title","%"+title+"%");// seteaza param "title" cu val primita ca param (folosind op % pt a cauta cuvinte care contin titlul dat)
        em.close();
        return query.getResultList();// return lista cu toate albumele gasite
    }
}
