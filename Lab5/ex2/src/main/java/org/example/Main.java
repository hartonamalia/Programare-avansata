package org.example;

import freemarker.template.TemplateException;
import org.example.commands.*;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidDocumentException;
import org.example.exceptions.InvalidPathException;
import org.example.model.Catalog;
import org.example.model.Document;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static Catalog catalog;
    public static void main(String[] args) {
        Main app = new Main();

        // testeaza fiecare comanda si da catch la erori
        try {
            app.testSaveCommand();
            app.testLoadCommand();
            app.testListCommand();
            app.testViewCommand();
            app.testReportCommand();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (InvalidCatalogException e){
            e.printStackTrace();
        }
        catch (InvalidDocumentException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (InvalidPathException e) {
            e.printStackTrace();
        }
    }

    /*
     * Metoda care salveaza un fisier in format JSON
     * @throws IOException
     */
    private void testSaveCommand() throws IOException, InvalidCatalogException, URISyntaxException, InvalidDocumentException, InvalidPathException {

        catalog = new Catalog("Laboratoare");
        Document doc = new Document("oop1",
                "OOP Lab 1", "https://sites.google.com/view/fii-poo/2023/laboratories");
        doc.addTag("type", "Exercise");
        doc.addTag("language", "C++");

        AddCommand addCommand = new AddCommand(catalog,doc);
        addCommand.execute();
        Document article = new Document("tw1",
                "TW 1", "https://profs.info.uaic.ro/~busaco/teach/courses/web/web-projects.html");
        article.addTag("type", "Article");
        //catalog.addDoc(article);
        addCommand.setDocument(article);
        addCommand.execute();

        SaveCommand saveCommand = new SaveCommand(catalog,"C:/Users/harto/Desktop/catalog.json");
        saveCommand.execute();




    }
    /*
     * Metoda care incarca un fisier in format JSON
     * @throws IOException
     */
    private void testLoadCommand() throws InvalidCatalogException, IOException, InvalidPathException {
        Catalog catalog = new Catalog();
        LoadCommand loadCommand = new LoadCommand(catalog,"C:/Users/harto/Desktop/catalog.json");
        loadCommand.execute();
        catalog = loadCommand.getCatalog();
        System.out.println(catalog);
    }

    /*
     * Metoda care afiseaza toate documentele dintr-un document
     * @throws InvalidCatalogException
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidDocumentException
     */
    private void testListCommand() throws InvalidCatalogException, IOException, URISyntaxException, InvalidDocumentException {

        Document doc = new Document("pa1",
                "PA Lab 1", "https://profs.info.uaic.ro/~acf/java/labs/lab_01.html");
        doc.addTag("type", "Exercise");
        doc.addTag("language", "JAVA");
        //catalog.addDoc(doc);

        AddCommand addCommand = new AddCommand(catalog,doc);
        addCommand.execute();
        ListCommand listCommand = new ListCommand(catalog);
        listCommand.execute();
        Document article = new Document("pa2",
                "PA 2", "https://profs.info.uaic.ro/~acf/java/labs/lab_02.html");
        article.addTag("type", "Lab");
        //catalog.addDoc(article);
        addCommand.setDocument(article);
        addCommand.execute();
    }

    /*
     * Metoda deschide un document folosind aplicația nativă a sistemului de operare, clasa Desktop
     * @throws InvalidDocumentException
     * @throws IOException
     * @throws URISyntaxException
     */
    private void testViewCommand() throws InvalidDocumentException,IOException, URISyntaxException {
        ViewCommand viewCommand = new ViewCommand(catalog.findById("pa2"));
        viewCommand.execute();

    }

    /*
     * Metoda creează (și deschide) un HTML reprezentând conținutul catalogului, am utilizat FreeMaker
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidDocumentException
     * @throws TemplateException
     * @throws InvalidCatalogException
     */
    private void testReportCommand() throws IOException, URISyntaxException, InvalidDocumentException, TemplateException, InvalidCatalogException {
        ReportCommand reportCommand = new ReportCommand(catalog);
        reportCommand.execute();
    }
}