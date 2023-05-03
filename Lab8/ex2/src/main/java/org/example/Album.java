package org.example;
public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private int artistId;

    public Album(int releaseYear, String title, int artistId) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistId = artistId;
    }

    public Album(int id, int releaseYear, String title, int artistId) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artistId = artistId;
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

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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
