package com.victor.scheduler.repositories;

import com.victor.scheduler.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Boolean existsByReportTitle(String reportTitle);
}
