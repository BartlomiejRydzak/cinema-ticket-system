DROP TABLE IF EXISTS movie CASCADE;
DROP TABLE IF EXISTS show CASCADE;

DROP SEQUENCE IF EXISTS show_seq;
DROP SEQUENCE IF EXISTS movie_seq;

CREATE SEQUENCE show_seq start WITH 1 increment BY 1;
CREATE SEQUENCE movie_seq start WITH 1 increment BY 1;

CREATE TABLE IF NOT EXISTS show
(
    id INTEGER,
    date timestamp,
    room INTEGER,
    movie_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie
(
    id INTEGER,
    title VARCHAR(255),
    description TEXT,
    duration_minutes INTEGER,
    image_url TEXT,
    PRIMARY KEY (id)
);

ALTER TABLE show
    ADD CONSTRAINT FK_movie FOREIGN KEY (movie_id) REFERENCES movie