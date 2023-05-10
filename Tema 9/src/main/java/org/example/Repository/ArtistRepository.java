package org.example.Repository;
import javax.persistence.*;


import org.example.Entity.Artist;
import org.example.EntityManagerFactoryUtil;

import java.util.List;

/**
 * Clasa mosteneste metodele generice ale clasei AbstractRepository, ceea ce face codul mai modular
 * si mai usor de intretinut
 */
public class ArtistRepository extends AbstractRepository<Artist> {
    public ArtistRepository() {
        super(Artist.class);
    }
}


