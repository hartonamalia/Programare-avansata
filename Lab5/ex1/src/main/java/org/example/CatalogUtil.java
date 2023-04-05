package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    /*
     * Metoda care salveaza un obiect de tip catalog in format JSON
     * @param catalog
     * @param path
     * @throws IOException
     */
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), catalog);
    }

    /*
     * Metoda care incarca un obiect de tip catalog in format JSON
     * @param path
     * @throws InvalidCatalogException
     * @throws IOException
     */
    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Catalog catalog = mapper.readValue(new File(path), Catalog.class);
        if(catalog==null)
        {
            throw new InvalidCatalogException("Catalog Invalid");
        }
        return catalog;
    }

}
