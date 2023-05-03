package org.example.model;

import org.example.model.Album;
import org.example.model.Genre;

public class AlbumGenre {
    private int id;
    private Album album;
    private Genre genre;

    public AlbumGenre(int id, Album album, Genre genre) {
        this.id = id;
        this.album = album;
        this.genre = genre;
    }

    public AlbumGenre(Album album, Genre genre) {
        this.album = album;
        this.genre = genre;
    }
}
