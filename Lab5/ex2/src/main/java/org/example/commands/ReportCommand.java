package org.example.commands;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.model.Catalog;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.Desktop;


import freemarker.template.Configuration;
import org.example.exceptions.InvalidCatalogException;


public class ReportCommand implements Command{

    private Catalog catalog;
    public ReportCommand(Catalog catalog)
    {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    /*
     * Metoda creează (și deschide) un HTML reprezentând conținutul catalogului, am utilizat FreeMaker
     * @throws IOException
     * @throws TemplateException
     * @throws InvalidCatalogException
     */
    public void execute() throws IOException, TemplateException, InvalidCatalogException {
        if(catalog == null)
        {
            throw new InvalidCatalogException("Catalogul " + catalog.getName() + " nu exista!");
        }
        else {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31); // init o instanta de "configuration"
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");//template-urile sunt stocate intr un director numit "templates"

            Template template = cfg.getTemplate("catalog.ftl");// preia template ul din catalog ftl

            Map<String, Object> data = new HashMap<>();//stochez datele intr un obiect "Map"
            data.put("documents", catalog.getDocs());

            StringWriter writer = new StringWriter();
            template.process(data, writer);

            String htmlReport = writer.toString();// stochez intr un sir rez

            try (FileWriter fileWriter = new FileWriter("catalog.html")) {
                fileWriter.write(htmlReport);
            }

            Desktop.getDesktop().browse(new File("catalog.html").toURI());
        }
    }


}
