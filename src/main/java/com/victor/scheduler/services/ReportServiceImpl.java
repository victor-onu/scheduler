package com.victor.scheduler.services;

import com.victor.scheduler.config.Setting;
import com.victor.scheduler.dtos.MailDto;
import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.exceptions.CustomUniqueConstraintViolationException;
import com.victor.scheduler.models.Report;
import com.victor.scheduler.repositories.RecipientRepository;
import com.victor.scheduler.repositories.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public Report uploadReport(ReportDto reportDto) {
        if (reportRepository.existsByReportTitle(reportDto.getReportTitle())){
            throw new CustomUniqueConstraintViolationException("Report title already exists");
        }
        Report report = new Report();
        this.modelMapper.map(reportDto, report);
        return reportRepository.save(report);
    }

//    @Scheduled(initialDelay = 2000L, fixedDelay = 5000L)
//    void ScheduleOneOff() throws InterruptedException {
//        String[] allEmail = recipientRepository.allEmail();
//        MailDto mail = MailDto.builder().mailFrom(Setting.FROM_EMAIL).mailTo(allEmail).mailSubject("Subject")
//                .mailTemplateName("report-email-template.ftl").build();
//        Map<String, Object> model = new HashMap<String, Object>();
////        model.put("name", user.getFirstName());
////        model.put("password", dto.getPassword());
////        model.put("username", user.getEmail());
//        // send mail
//         mailService.sendSimpleMessage(mail, model);
//    }
}
