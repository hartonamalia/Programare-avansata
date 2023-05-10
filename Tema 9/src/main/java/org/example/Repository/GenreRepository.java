package org.example.Repository;

import org.example.Entity.Genre;
import org.example.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreRepository extends AbstractRepository<Genre>{
    public GenreRepository(){
        super(Genre.class);
    }

    /**
     * Metoda primeste un parametru de tip int care reprezinta id-ul genului cautat
     * @param id
     * @return
     */
    public Genre findById(int id){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        Genre genre = em.find(Genre.class,id);// se cauta genul dupa id
        em.close();
        return genre;// return genul gasit
    }

    /**
     * Metoda primeste un parametru de tip String care reprezinta numele genului cautat
     * @param name
     * @return
     */
    public List<Genre> findByName(String name){
        EntityManager em = EntityManagerFactoryUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Genre> query = em.createNamedQuery("Genre.findByName",Genre.class);
        // se creeaza un obiect TQ cu ajutorul caruia se cauta genurile dupa nume, cu cond ca acesta sa contina subsirul specificat in param name
        query.setParameter("name","%"+name+"%");
        List<Genre> genres= query.getResultList();// rez sunt stocate intr o lista de genuri
        em.close();
        return genres;

    }
}
