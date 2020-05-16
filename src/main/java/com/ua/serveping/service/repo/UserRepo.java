package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.domains.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}
