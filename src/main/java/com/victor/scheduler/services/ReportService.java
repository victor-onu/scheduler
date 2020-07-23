package com.victor.scheduler.services;

import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.models.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
@Transactional
public interface ReportService {
    Report uploadReport(ReportDto reportDto, MultipartFile multipartFile);

    Collection<Report> findAllReports();
}
