package com.victor.scheduler.services;

import com.victor.scheduler.config.FileStorageProperties;
import com.victor.scheduler.config.Setting;
import com.victor.scheduler.dtos.MailDto;
import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.exceptions.CustomUniqueConstraintViolationException;
import com.victor.scheduler.models.Report;
import com.victor.scheduler.repositories.RecipientRepository;
import com.victor.scheduler.repositories.ReportRepository;
import com.victor.scheduler.services.storage.FileStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired MailServiceImpl mailService;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Override
    public Report uploadReport(ReportDto reportDto, MultipartFile multipartFile) {
        if (reportRepository.existsByReportTitle(reportDto.getReportTitle())){
            throw new CustomUniqueConstraintViolationException("Report title already exists");
        }
        Report report = new Report();
        this.modelMapper.map(reportDto, report);
        reportRepository.save(report);

        String filePath = null;
        filePath = fileStorageService.storeFile(multipartFile);
        report.setURl(filePath);
        report.setReportDocument(multipartFile.getOriginalFilename());
        System.out.println(filePath);
        reportRepository.save(report);
        return report;
    }
}
