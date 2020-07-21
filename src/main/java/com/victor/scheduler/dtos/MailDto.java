package com.victor.scheduler.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class MailDto {
    
    private String mailFrom;

    private String[] mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private File attachments;

    private Map<String, Object> model;
     
    private String mailTemplateName;

    public MailDto(String from) {
      this.mailFrom=from;
    }
}
