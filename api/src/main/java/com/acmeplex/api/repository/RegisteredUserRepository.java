package com.acmeplex.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.acmeplex.api.model.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
}
