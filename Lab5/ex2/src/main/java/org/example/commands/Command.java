package org.example.commands;

import freemarker.template.TemplateException;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidDocumentException;
import org.example.exceptions.InvalidPathException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Command {
    void execute() throws IOException, InvalidCatalogException, URISyntaxException, InvalidDocumentException, TemplateException, InvalidPathException;

}
