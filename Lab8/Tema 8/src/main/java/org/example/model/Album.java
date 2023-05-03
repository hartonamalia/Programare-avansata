package org.example.model;

import java.util.List;

public class Album{
    private int id;
    private int releaseYear;
    private String title;
    private int artistId;

    public Album(int id, int releaseYear, String title, int artistId) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistId = artistId;
    }

    public Album(int releaseYear, String title, int artistId) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistId = artistId;
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



    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
