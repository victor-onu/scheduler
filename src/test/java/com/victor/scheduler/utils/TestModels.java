package com.victor.scheduler.utils;

import com.victor.scheduler.dtos.RecipientDto;

public class TestModels {
    public static RecipientDto addRecipientDto() {
        RecipientDto recipientDto = new RecipientDto("firstName", "lastName", "victoronushaibu@gmail.com");
        return recipientDto;
    }
}
