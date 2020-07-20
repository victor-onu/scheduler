package com.victor.scheduler.services;

import com.victor.scheduler.dtos.RecipientDto;
import com.victor.scheduler.exceptions.CustomUniqueConstraintViolationException;
import com.victor.scheduler.models.Recipient;
import com.victor.scheduler.repositories.RecipientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipientServiceImpl implements RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Recipient createRecipient(RecipientDto recipientDto) {
        if (recipientRepository.existsByEmail(recipientDto.getEmail())){
            throw new CustomUniqueConstraintViolationException("Email already exists");
        }
        Recipient recipient = new Recipient();
        this.modelMapper.map(recipientDto, recipient);
        return recipientRepository.save(recipient);
    }
}
