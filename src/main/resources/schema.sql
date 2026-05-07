DROP TABLE IF EXISTS show CASCADE;
DROP TABLE IF EXISTS movie CASCADE;
DROP TABLE IF EXISTS room CASCADE;
DROP TABLE IF EXISTS seat CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;

DROP SEQUENCE IF EXISTS show_seq;
DROP SEQUENCE IF EXISTS movie_seq;
DROP SEQUENCE IF EXISTS room_seq;
DROP SEQUENCE IF EXISTS seat_seq;
DROP SEQUENCE IF EXISTS ticket_seq;

CREATE SEQUENCE show_seq start WITH 1 increment BY 1;
CREATE SEQUENCE movie_seq start WITH 1 increment BY 1;
CREATE SEQUENCE room_seq start WITH 1 increment BY 1;
CREATE SEQUENCE seat_seq start WITH 1 increment BY 1;
CREATE SEQUENCE ticket_seq start WITH 1 increment BY 1;

CREATE TABLE IF NOT EXISTS show
(
    id INTEGER,
    date timestamp,
    movie_id INTEGER,
    room_id INTEGER,
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

CREATE TABLE IF NOT EXISTS room
(
    id INTEGER,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS seat
(
    id INTEGER,
    row_number INTEGER,
    seat_number INTEGER,
    room_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ticket
(
    id INTEGER,
    show_id INTEGER,
    seat_id INTEGER,
    PRIMARY KEY (id)
);

ALTER TABLE show
    ADD CONSTRAINT FK_movie FOREIGN KEY (movie_id) REFERENCES movie;
ALTER TABLE show
    ADD CONSTRAINT FK_room FOREIGN KEY (room_id) REFERENCES room;
ALTER TABLE seat
    ADD CONSTRAINT FK_room FOREIGN KEY (room_id) REFERENCES room;
ALTER TABLE ticket
    ADD CONSTRAINT FK_show FOREIGN KEY (show_id) REFERENCES show;
ALTER TABLE ticket
    ADD CONSTRAINT FK_seat FOREIGN KEY (seat_id) REFERENCES seat;
