package com.victor.scheduler.unit.services;

import com.victor.scheduler.dtos.RecipientDto;
import com.victor.scheduler.exceptions.CustomUniqueConstraintViolationException;
import com.victor.scheduler.models.Recipient;
import com.victor.scheduler.repositories.RecipientRepository;
import com.victor.scheduler.services.RecipientServiceImpl;
import com.victor.scheduler.utils.TestModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecipientServiceTest {
    @Mock
    private RecipientRepository recipientRepository;

    @Mock
    private Recipient recipient;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RecipientServiceImpl recipientService;

    @Test
    void addRecipient_ShouldAddSuccessfully() throws Exception{
        RecipientDto recipientDto = TestModels.addRecipientDto();

        given(recipientRepository.save(any(Recipient.class))).willAnswer(invocation ->  invocation.getArgument(0));

        recipientService.createRecipient(recipientDto);
        verify(recipientRepository).save(any(Recipient.class));
    }

    @Test
    void addRecipient_ShouldThrowExceptionWhenEmailExists() throws Exception{
        RecipientDto recipientDto = TestModels.addRecipientDto();

        given(recipientRepository.existsByEmail(recipientDto.getEmail())).willReturn(true);
        assertThrows(CustomUniqueConstraintViolationException.class, () -> {
            recipientService.createRecipient(recipientDto);
        });
        verify(recipientRepository, never()).save(any(Recipient.class));
    }
}
