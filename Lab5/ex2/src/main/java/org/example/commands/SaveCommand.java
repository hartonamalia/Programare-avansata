package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Catalog;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathException;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command{

    private Catalog catalog;
    private String path;
    public SaveCommand(Catalog catalog,String path)
    {
        this.catalog = catalog;
        this.path = path;
    }

    /*
     * Metoda salveaza un obiect Catalog pe disc
     * @throws IOException
     * @throws InvalidCatalogException
     * @throws InvalidPathException
     */
    public void execute() throws IOException, InvalidCatalogException, InvalidPathException {
        if(catalog == null) {
            throw new InvalidCatalogException("Catalogul " + catalog.getName() + " nu exista!");
        }
        if(path == null)
        {
            throw new InvalidPathException("Path-ul este null!");
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), catalog); // ob catalog este serializat in format json si apoi scris in fisierul specificat
        }
    }
}
