package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    List<Role> findByNameIn(List<String> name);
}
