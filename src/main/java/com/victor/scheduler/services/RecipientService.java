package com.victor.scheduler.services;

import com.victor.scheduler.dtos.RecipientDto;
import com.victor.scheduler.models.Recipient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface RecipientService {
    Recipient createRecipient(RecipientDto recipientDto);
}
