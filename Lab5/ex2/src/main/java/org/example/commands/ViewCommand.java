package org.example.commands;

import org.example.model.Document;
import org.example.exceptions.InvalidDocumentException;

import java.io.File;
import java.net.URI;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
public class ViewCommand implements Command{
    private Document document;
    private Desktop desktop;
    public ViewCommand(Document document)
    {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Desktop getDesktop() {
        return desktop;
    }

    /*
     * Metoda deschide un document folosind aplicația nativă a sistemului de operare, clasa Desktop
     * @throws InvalidDocumentException
     * @throws IOException
     * @throws URISyntaxException
     */
    public void execute() throws InvalidDocumentException,IOException, URISyntaxException {
        if(document == null)
        {
            throw new InvalidDocumentException("Documentul cu id-ul " + document.getId() + " nu exista!");
        }
        else {
            Desktop desktop = Desktop.getDesktop();
            if (document.getLocation().startsWith("file://")) { //verific daca locatia documentului incepe cu file
                File file = new File(new URI(document.getLocation()));// creez un obiect de tip file
                desktop.open(file);// apoi se deschide fisierul
            } else {
                URI uri = new URI(document.getLocation());
                desktop.browse(uri);
            }
        }
    }
}
