package com.ua.serveping.service.repo;

import com.ua.serveping.service.models.domains.CheckReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckReportRepo extends JpaRepository<CheckReport, Long> {
}
