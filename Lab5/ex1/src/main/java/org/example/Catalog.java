package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Document> docs = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog(String name, List<Document> docs) {
        this.name = name;
        this.docs = docs;
    }

    public Catalog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }

    public void addDoc(Document doc) {
        docs.add(doc);
    }

    // caut un doc in lista dupa id..returneaza primul doc gasit sau null daca nu e gasit
    public Document findById(String id) {
        return docs.stream()
                .filter(doc -> doc.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", docs=" + docs +
                '}';
    }
}
