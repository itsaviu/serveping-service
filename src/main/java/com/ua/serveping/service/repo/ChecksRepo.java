package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.domains.Checks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChecksRepo extends JpaRepository<Checks, Long> {

    @Query("update Checks checks set checks.isDelete = true where checks.id = :id")
    @Modifying
    void softDeleteById(@Param("id") Long id);
}
