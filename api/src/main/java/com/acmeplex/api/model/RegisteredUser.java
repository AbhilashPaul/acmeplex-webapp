package com.acmeplex.api.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class RegisteredUser extends User{
    private String password;
    private Date memberSince;
}
