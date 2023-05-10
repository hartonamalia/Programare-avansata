package org.example.Utils;

import com.github.javafaker.Faker;
import org.example.Entity.Album;
import org.example.Entity.Artist;
import org.example.Repository.AlbumRepository;
import org.example.Repository.ArtistRepository;

public class FakeDate {
    /**
     * Metoda creeaza folosindu-se de biblioteca Faker artisti si albume si pentru a le insera in baza de date si pt a crea date fictive
     * genereaza niste date fictive
     * @param numArtist numarul de artisti
     * @param albumsPerArtist numarul de albume per artist
     */

    public static void createData(int numArtist,int albumsPerArtist){
        ArtistRepository artistRepository = new ArtistRepository();// creez ob
        AlbumRepository albumRepository = new AlbumRepository();
        Faker faker = new Faker();
        for(int i = 0; i < numArtist;i++){// pt fiecare artist
            String name = faker.name().fullName();// creeaza un nume aleator
            if(artistRepository.findByAttribute("name","Artist.findByName",name).isEmpty())//cauta in bd daca nu exista deja un artist cu acelasi nume ca cel facut de faker
            {
                Artist artist = new Artist();
                artist.setName(name);
                artistRepository.create(artist);
                for(int j = 0; j < albumsPerArtist; j++){
                    String title = faker.lorem().words(3).toString();// creeaza titlul albumului de lg 3
                    if(albumRepository.findByAttribute("title","Album.findByName",title).isEmpty()) {
                        Album album = new Album();
                        album.setTitle(title);
                        album.setReleaseYear(faker.number().numberBetween(2002, 2023));// an de lansare random
                        album.setArtist(artist);// iecare album este asociat cu artistul creat anterior si inserat in bd
                        albumRepository.create(album);
                    }
            }
            }
        }

    }
}
