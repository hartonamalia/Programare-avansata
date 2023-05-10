package org.example.Entity;

import javax.persistence.*;

@Entity // clasa de entitate JPA si trb mapata la o tabela din bd
@Table(name = "albums")
@NamedQuery(name="Album.findByName",query = "SELECT a FROM Album a WHERE a.title LIKE :title")
public class Album {

    @Id // pk a tabelului si trb mapata la col id in bd
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "artist",nullable = false)
    private Artist artist;

    public Album() {
    }
    public Album(int id, int releaseYear, String title, Artist artist) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }
}
