package com.acmeplex.api.model;

import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Entity
public class RegisteredUser extends User implements UserDetails {
    private String password;

    @CreationTimestamp
    private Date memberSince;

    private boolean annualFeePaid; // True if the annual fee has been paid

    private LocalDateTime annualFeePaidDate; // The date when the fee was last paid

    public RegisteredUser(String firstName, String lastName, String email, String address, String phoneNumber, String password, Date memberSince) {
        super(firstName, lastName, email, address, phoneNumber);
        this.password = password;
        this.memberSince = memberSince;
    }

    public RegisteredUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
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

    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }

    public void setAnnualFeePaid(boolean annualFeePaid) {
        this.annualFeePaid = annualFeePaid;
    }

    public LocalDateTime getAnnualFeePaidDate() {
        return annualFeePaidDate;
    }

    public void setAnnualFeePaidDate(LocalDateTime annualFeePaidDate) {
        this.annualFeePaidDate = annualFeePaidDate;
    }

    /**
     * Check if the membership has expired.
     *
     * @return True if the current date is after the annualFeePaidDate + 1 year, otherwise False.
     */
    public boolean isMembershipExpired() {
        if (annualFeePaidDate == null) {
            return true; // Expired if no payment date exists
        }
        LocalDateTime expiryDate = annualFeePaidDate.plusYears(1);
        return LocalDateTime.now().isAfter(expiryDate);
    }

}
