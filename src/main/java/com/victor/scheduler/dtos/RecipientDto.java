package com.victor.scheduler.dtos;

import com.victor.scheduler.models.Oneoff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @DateTimeFormat
    private Date startDate;

    @DateTimeFormat
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Oneoff status;
}
