package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadView();
        }
        catch (InvalidCatalogException | IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Metoda care salveaza un fisier in format JSON
     * @throws IOException
     */
    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("Laboratoare");
        Document doc = new Document("oop1",
                "OOP Lab 1", "https://sites.google.com/view/fii-poo/2023/laboratories");
        doc.addTag("type", "Exercise");
        doc.addTag("language", "C++");
        catalog.addDoc(doc);
        Document article = new Document("tw1",
                "TW 1", "https://profs.info.uaic.ro/~busaco/teach/courses/web/web-projects.html");
        article.addTag("type", "Article");
        catalog.addDoc(article);
        CatalogUtil.save(catalog, "C:/Users/harto/Desktop/catalog.json");
    }
    /*
     * Metoda care incarca si afiseaza continutul unui catalog salvat anterior intr-un fisier in format JSON
     * @throws IOException
     */
    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("C:/Users/harto/Desktop/catalog.json");
        System.out.println(catalog);
    }
}