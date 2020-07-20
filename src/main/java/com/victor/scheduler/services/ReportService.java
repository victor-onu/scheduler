package com.victor.scheduler.services;

import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.models.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ReportService {
    Report uploadReport(ReportDto reportDto);
}
