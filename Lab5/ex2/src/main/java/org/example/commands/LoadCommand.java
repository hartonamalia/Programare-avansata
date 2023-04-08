package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Catalog;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathException;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command{
    private Catalog catalog;
    private String path;
    public LoadCommand(Catalog catalog,String path)
    {
        this.catalog = catalog;
        this.path = path;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*
     * Metoda incarca de la un anumit path dat intr-un obiect Catalog
     * @throws IOException
     * @throws InvalidCatalogException
     * @throws InvalidPathException
     */

    public void execute() throws IOException, InvalidCatalogException, InvalidPathException {
        if(catalog == null)
        {
            throw new InvalidCatalogException("Catalogul " + catalog.getName() + " nu exista!");

        }
        else if(path == null)
        {
            throw new InvalidPathException("Path-ul este null!");
        }
        else
        {
            ObjectMapper mapper = new ObjectMapper();
            catalog = mapper.readValue(new File(path), Catalog.class);
        }

    }
}
