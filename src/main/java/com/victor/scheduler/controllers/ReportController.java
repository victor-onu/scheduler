package com.victor.scheduler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.scheduler.apiresponse.ApiResponse;
import com.victor.scheduler.dtos.ReportDto;
import com.victor.scheduler.models.Report;
import com.victor.scheduler.services.ReportService;
import com.victor.scheduler.services.storage.FileStorageService;
import com.victor.scheduler.validator.CustomFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    CustomFileValidator customFileValidator;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ReportService reportService;


    @PostMapping(value = "/report", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Report>> uploadReport(
            @Valid @RequestParam(value = "upload_report_request") String title,
            @RequestParam(value = "report_document", required = true) MultipartFile multipartFile
            ) throws JsonProcessingException {

        ReportDto reportDto  = new ObjectMapper().readValue(title, ReportDto.class);
        ApiResponse<Report> response = new ApiResponse<>(HttpStatus.OK);
        Report report = reportService.uploadReport(reportDto, multipartFile);
        response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Upload successful");
        response.setData(report);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/report")
    public ResponseEntity<ApiResponse<Collection<Report>>> getAllReports(){
        Collection<Report> allReports = reportService.findAllReports();
        ApiResponse<Collection<Report>> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Retrieved Reports successfully");
        response.setData(allReports);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/uploads/{fileName:.+}")
    public ResponseEntity <Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }




}
