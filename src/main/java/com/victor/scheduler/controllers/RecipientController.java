package com.victor.scheduler.controllers;

import com.victor.scheduler.apiresponse.ApiResponse;
import com.victor.scheduler.dtos.RecipientDto;
import com.victor.scheduler.models.Recipient;
import com.victor.scheduler.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RecipientController {
    @Autowired
    private RecipientService recipientService;

    @PostMapping("/recipient")
    public ResponseEntity<ApiResponse<Recipient>> addRecipient(@Valid @RequestBody RecipientDto recipientDto){
        Recipient newRecipient=recipientService.createRecipient(recipientDto);
        ApiResponse<Recipient> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Recipient added successfully");
        response.setData(newRecipient);
        return new ResponseEntity(response, HttpStatus.CREATED);

    }
}
