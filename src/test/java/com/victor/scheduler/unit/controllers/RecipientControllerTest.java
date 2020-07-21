package com.victor.scheduler.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.scheduler.controllers.RecipientController;
import com.victor.scheduler.dtos.RecipientDto;
import com.victor.scheduler.exceptions.CustomUniqueConstraintViolationException;
import com.victor.scheduler.models.Recipient;
import com.victor.scheduler.services.RecipientService;
import com.victor.scheduler.utils.TestModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.victor.scheduler.utils.TestUtils.asJsonString;
import static org.hamcrest.Matchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipientController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RecipientControllerTest {

    private String path = "/api/recipient";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    RecipientService recipientService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    void addRecipient_ShouldAddSuccessfully() throws Exception{
        RecipientDto recipientDto = TestModels.addRecipientDto();

        Recipient recipient = new Recipient(1L, "firstName", "lastName", "victoronushaibu@gmail.com");
        given(recipientService.createRecipient(recipientDto)).willReturn(recipient);
        mockMvc.perform(MockMvcRequestBuilders.post(path).contentType("application/json").content(asJsonString(recipientDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".message").value("Recipient added successfully"));
    }

    @Test
    void addRecipient_ShouldThrowUniqueConstraintExceptionWhenAddRecipientWithExistingEmail() throws Exception{
        RecipientDto recipientDto = TestModels.addRecipientDto();

        doThrow(CustomUniqueConstraintViolationException.class).when(recipientService).createRecipient(recipientDto);
        mockMvc.perform(post(path).contentType("application/json").content(asJsonString(recipientDto)))
                .andExpect(status().isConflict());
    }

}
