package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Playlist {
    private final String name;
    private final Date creationTimestamp;
    private final List<Album> albums;

    public Playlist(String name) {
        this.name = name;
        this.creationTimestamp = new Date();
        this.albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", albums=" + albums +
                '}';
    }
}
