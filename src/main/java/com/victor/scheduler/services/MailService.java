package com.victor.scheduler.services;

import com.victor.scheduler.dtos.MailDto;

import java.util.Map;

public interface MailService {
    public void sendSimpleMessage(MailDto mail, Map<String, Object> model);

}
