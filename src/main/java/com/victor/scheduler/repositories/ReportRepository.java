package com.victor.scheduler.repositories;

import com.victor.scheduler.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.File;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Boolean existsByReportTitle(String reportTitle);

    @Query(value = "SELECT s.reportDocument FROM Report s where s.reportDocument = 'index.jpg'")
    String getFileName();

}
