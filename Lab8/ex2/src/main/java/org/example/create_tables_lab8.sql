CREATE TABLE artists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE albums (
    id SERIAL PRIMARY KEY,
    release_year INTEGER,
    title VARCHAR(255),
    artist_id INTEGER REFERENCES artists(id)
);

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE album_genres (
    album_id INTEGER REFERENCES albums(id),
    genre_id INTEGER REFERENCES genres(id),
    PRIMARY KEY (album_id, genre_id)
);