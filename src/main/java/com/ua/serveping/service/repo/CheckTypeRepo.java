package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.CheckValue;
import com.ua.serveping.service.models.domains.CheckType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckTypeRepo extends JpaRepository<CheckType, Long> {

    Optional<CheckType> findByCheckValue(CheckValue checkValue);
}
