*Am folosit un connection pool pentru a manageria conexiunile la baza de date, HikariCP 
*Am creat modele pentru toate tabelele Album, Genre, AlbumGenre, Artist
*Am creat DAO pentru toate modelele AlbumDAO, GenreDAO, AlbumGenreDAO, ArtistDAO ce contin diferite metode precum
creat, findById, findByName, remove, removeAll etc
*Am creat o clasa DataImporter ce imi incarca datele din fisierul csv, le parseaza si le incarca in baza
de date folosindu-se de DAO-uri.
