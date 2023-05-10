package org.example.Entity;

import org.example.AlbumGenreKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album_genres")
@NamedQueries({
        @NamedQuery(name = "AlbumGenre.findByAlbum",
                query = "SELECT a FROM AlbumGenre a WHERE a.album.id = :album"),
        @NamedQuery(name = "AlbumGenre.findByGenre",
                query = "SELECT a FROM AlbumGenre a WHERE a.genre.id = :genre")
})
public class AlbumGenre  {
    @EmbeddedId
    private AlbumGenreKey id;
    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id",nullable = false)
    public Album album;
    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id",nullable = false)
    public Genre genre;

    public AlbumGenre() {
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public AlbumGenreKey getId() {
        return id;
    }

    public void setId(AlbumGenreKey id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "AlbumGenre{" +
                "id=" + id +
                ", album=" + album +
                ", genre=" + genre +
                '}';
    }
}
