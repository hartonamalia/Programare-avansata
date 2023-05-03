package org.example;


import org.example.Entity.Artist;
import org.example.Repository.ArtistRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepository();

        Artist newArtist = new Artist();
        newArtist.setName("Puya");
        artistRepository.create(newArtist);

        Artist foundArtist = artistRepository.findById(newArtist.getId());
        System.out.println("Found artist: " + foundArtist.getName());

        List<Artist> artists = artistRepository.findAll();
        System.out.println("All artists: ");
        for (Artist artist : artists) {
            System.out.println(artist.getName());
        }

        EntityManagerFactoryUtil.closeEntityManagerFactory();
    }
}
