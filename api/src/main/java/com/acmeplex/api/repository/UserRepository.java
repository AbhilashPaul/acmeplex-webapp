package com.acmeplex.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acmeplex.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
