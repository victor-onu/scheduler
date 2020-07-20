package com.victor.scheduler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.scheduler.apiresponse.ApiResponse;
import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.models.Report;
import com.victor.scheduler.services.FileUploadService;
import com.victor.scheduler.services.ReportService;
import com.victor.scheduler.validator.CustomFileValidator;
import com.victor.scheduler.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping(value = "/api")
public class ReportController {
    @Autowired
    CustomFileValidator customFileValidator;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ReportService reportService;


    @PostMapping(value = "/report", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Report>> uploadReport(
            @Valid @RequestParam(value = "upload_report_request") ReportDto reportDto,
            @RequestParam(value = "report_document", required = true) MultipartFile multipartFile,
            BindingResult bindingResult
            ){


        ApiResponse<Report> ar = new ApiResponse<>(HttpStatus.OK);
        String filePath = null;
        customFileValidator.validate(multipartFile, bindingResult);
        if (bindingResult.hasErrors()) {
            ar.addValidationErrors(bindingResult.getFieldErrors());
            ar.setError("Validation Failed");
            return new ResponseEntity<>(ar, HttpStatus.BAD_REQUEST);
        }

        Report report = reportService.uploadReport(reportDto);
        File file = WebUtils.convertMultiPartToFile(multipartFile);
        filePath = fileUploadService.storeFile(file);
        report.setReportDocument(filePath);
        ar = new ApiResponse<>(HttpStatus.OK);
        ar.setMessage("Upload successful");
        ar.setData(report);
        return new ResponseEntity<>(ar, HttpStatus.CREATED);
    }


}
