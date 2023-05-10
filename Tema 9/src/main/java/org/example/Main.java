package org.example;


import org.example.Entity.Album;
import org.example.Entity.AlbumGenre;
import org.example.Entity.Artist;
import org.example.Entity.Genre;
import org.example.Repository.AlbumGenreRepository;
import org.example.Repository.AlbumRepository;
import org.example.Repository.ArtistRepository;
import org.example.Repository.GenreRepository;
import org.example.Utils.FakeDate;

import java.util.List;

import static org.example.Utils.FakeDate.createData;

public class Main {

    public static void main(String[] args) {

        ArtistRepository artistRepository = new ArtistRepository();// creez instante al repositoryurilor
        AlbumRepository albumRepository = new AlbumRepository();
        GenreRepository genreRepository = new GenreRepository();
        AlbumGenreRepository albumGenreRepository = new AlbumGenreRepository();

        int numArtist = 100;
        int albumsPerArtist = 10;
        long startTime = System.currentTimeMillis();
        createData(numArtist,albumsPerArtist);// generez date de test din clasa fakedate
        long endtime = System.currentTimeMillis();
        long duration = endtime - startTime;
        System.out.println(duration);
       EntityManagerFactoryUtil.closeEntityManagerFactory();

    }
}
