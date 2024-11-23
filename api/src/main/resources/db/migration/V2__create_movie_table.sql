CREATE TABLE Movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    duration VARCHAR(50),
    genre VARCHAR(50),
    movieRating ENUM('G', 'PG', 'PG_13', 'R', 'NC_17'),
    imageUrl VARCHAR(255)
);