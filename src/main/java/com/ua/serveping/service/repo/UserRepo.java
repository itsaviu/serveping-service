package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.domains.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByEmailId(String emailId);
}
