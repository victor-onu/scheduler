package com.victor.scheduler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    //@NotBlank(message = "Report title cannot be empty")
    private String reportTitle;
    private String reportDescription;
}
