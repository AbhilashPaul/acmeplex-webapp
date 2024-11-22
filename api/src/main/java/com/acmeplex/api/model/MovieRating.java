package com.acmeplex.api.model;

public enum MovieRating {
    G("General Audiences"),
    PG("Parental Guidance Suggested"),
    PG_13("Parents Strongly Cautioned"),
    R("Restricted"),
    NC_17("Adults Only");

    private final String description;

    MovieRating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
