package org.example.Repository;

import org.example.AlbumGenreKey;
import org.example.Entity.Album;
import org.example.Entity.AlbumGenre;
import org.example.Entity.Genre;
import org.example.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumGenreRepository {

    /**
     * Metoda insereaza in baza de date o instanta noua de tip AlbumGenre in bd
     * @param albumGenre
     */
    public void create(AlbumGenre albumGenre){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();// se creeaza o noua instanta
        em.getTransaction().begin();// se incepe o tranzactie pt a asigura integritatea datelor pe parcursul operatiei de inserare
        Album mergedAlbum = em.merge(albumGenre.getAlbum());//// verif daca entitatea Album este deja in bd, iar daca nu o creeaza
        albumGenre.setAlbum(mergedAlbum);// se actualizeaza
        Genre mergedGenre= em.merge(albumGenre.getGenre());
        albumGenre.setGenre(mergedGenre);
        em.persist(albumGenre);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Metoda cauta in baza de date o instanta de tip AlbumGenre cu o anumita cheie (albumId,genreId)
     * @param albumId
     * @param genreId
     * @return
     */
    public AlbumGenre findById(Integer albumId, Integer genreId) {
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        AlbumGenreKey albumGenreId = new AlbumGenreKey(albumId, genreId); // se creeaza un nou obiect
        AlbumGenre albumGenre = em.find(AlbumGenre.class, albumGenreId); // se cauta in bd pt a gasi instanta de albumgenre
        em.close(); // inchide conexiunea la bd
        return albumGenre;//return instanta gasita sau null, daca nu exista nicio instanta cu acea cheie in bd
    }

    /**
     * Metoda returneaza o lista de instante de tip AlbumGenre cu toate albumele cu un anumit id
     * @param albumId
     * @return
     */
    public List<AlbumGenre> findByAlbumId(Integer albumId){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<AlbumGenre> query = em.createNamedQuery("AlbumGenre.findByAlbum",AlbumGenre.class);//creez un obiect care rep interogarea de cautare a instantei albumgenre
        query.setParameter("album",albumId);// setez param interogarii la valoarea albumId
        return query.getResultList();// return o lista cu toate instantele de tip albumgenre din bd
    }
    /**
     * Metoda returneaza o lista de instante de tip AlbumGenre cu toate genurile cu un anumit id
     * @param genreId
     * @return
     */
    public List<AlbumGenre> findByGenreId(Integer genreId){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<AlbumGenre> query = em.createNamedQuery("AlbumGenre.findByGenre",AlbumGenre.class);
        query.setParameter("genre",genreId);
        return query.getResultList();
    }



}
