package org.example;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//rep o cheie compusa pt entitatea AlbumGenre din bd.. Aceasta cheie este folosita pt a identifica in mod unic un obiect AlbumGenre
@Embeddable //ob poate fi incorporat in alte entitati
public class AlbumGenreKey implements Serializable {
    @Column(name = "album_id")
    private Integer albumId;

    @Column(name = "genre_id")
    private Integer genreId;

    public AlbumGenreKey() {
    }

    public AlbumGenreKey(Integer albumId, Integer genreId) {
        this.albumId = albumId;
        this.genreId = genreId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumGenreKey that = (AlbumGenreKey) o;
        return Objects.equals(albumId, that.albumId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, genreId);
    }
}

