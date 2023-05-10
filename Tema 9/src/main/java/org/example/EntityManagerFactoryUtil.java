package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


// Clasa EntityManagerFactoryUtil este o clasa utilitara care creeaza EntityManagerFactory, care poate fi utilizat pentru a crea EntityManager
// Utilizarea unui singleton asigura că se creeaza un singur EntityManagerFactory
public class EntityManagerFactoryUtil {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() { // verif ca ob EMF este creat doar o sg data
        if (entityManagerFactory == null) {
            synchronized (EntityManagerFactoryUtil.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("musicPU");
                }
            }
        }
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null) { // daca cumva exista, inchide
            entityManagerFactory.close();
        }
    }
}
