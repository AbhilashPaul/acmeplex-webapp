package com.acmeplex.api.model;

import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
public class RegisteredUser extends User{
    private String password;

    @CreationTimestamp
    private Date memberSince;

    public RegisteredUser(String firstName, String lastName, String email, String address, String phoneNumber, String password, Date memberSince) {
        super(firstName, lastName, email, address, phoneNumber);
        this.password = password;
        this.memberSince = memberSince;
    }

    public RegisteredUser() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
}
