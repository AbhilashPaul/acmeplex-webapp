CREATE TABLE MovieTheatre (
    movieId BIGINT NOT NULL,
    theatreId BIGINT NOT NULL,
    PRIMARY KEY (movieId, theatreId),
    FOREIGN KEY (movieId) REFERENCES Movie(id) ON DELETE CASCADE,
    FOREIGN KEY (theatreId) REFERENCES Theatre(id) ON DELETE CASCADE
);