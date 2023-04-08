package org.example.commands;

import org.example.model.Catalog;
import org.example.model.Document;
import org.example.exceptions.InvalidCatalogException;

public class ListCommand implements Command{
    private Catalog catalog;

    /*
     * Metoda afiseaza toate documentele dintr-un catalog
     * @param catalog
     */
    public ListCommand(Catalog catalog)
    {
        this.catalog = catalog;
    }
    public void execute() throws InvalidCatalogException {
        if (catalog == null) {
            throw new InvalidCatalogException("Catalogul " + catalog.getName() + " nu exista!");
        } else {
            for (Document doc : catalog.getDocs())
                System.out.println(doc); // afisez toate doc din catalog
        }
    }
}

