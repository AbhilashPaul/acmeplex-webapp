CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    phoneNumber VARCHAR(20)
);

CREATE TABLE RegisteredUser (
    userId BIGINT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    memberSince DATE NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(id)
);
