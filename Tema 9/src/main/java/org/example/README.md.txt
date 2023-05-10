*Am creat clasa Artist ce este o clasa entitate care reprezinta tabelul artists
*Am creat clasa Album ce este o clasa entitate care reprezinta tabelul albums
*Am creat clasa Genre ce este o clasa entitate care reprezinta tabelul genres
*Am creat clasa AlbumGenre ce este o clasa entitate care reprezinta tabelul album_genres
*Am creat clasa abstract AbstractRepository ce foloseste generics pentru a crea metode de creare, cautare dupa id, cautare dupa un NamedQuery
*Am creat clasa ArtistRepository, de tip repository care gestioneaza operatiile pentru entitatea Artist si extinde AbstractRepository
*Am creat clasa AlbumRepository, de tip repository care gestioneaza operatiile pentru entitatea Album si extinde AbstractRepository
*Am creat clasa GenreRepository, de tip repository care gestioneaza operatiile pentru entitatea Genre si extinde AbstractRepository
*Am creat clasa AlbumGenreRepository, de tip repository care gestioneaza operatiile pentru entitatea AlbumGenre
*Am folosit Faker pentru a-mi crea instante de artisti si albume si a le insera in baza de date
*In main am apelat metoda createData si am calculat care este timpul de executie pentru a crea si insera in baza de date
