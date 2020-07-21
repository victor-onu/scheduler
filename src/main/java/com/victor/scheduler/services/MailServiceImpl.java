package com.victor.scheduler.services;

import com.victor.scheduler.dtos.MailDto;
import com.victor.scheduler.utils.EmailTemplateResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
	EmailTemplateResolver emailTemplateEngine;

    @Async
    @Override
    public void sendSimpleMessage(MailDto mail, Map<String, Object> model) {

	try {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
		    StandardCharsets.UTF_8.name());
	    helper.setTo(mail.getMailTo());
	    helper.setText(emailTemplateEngine.processTemplateIntoString(mail.getMailTemplateName(), model), true);
	    helper.setSubject(mail.getMailSubject());
	    helper.setFrom(mail.getMailFrom());

		Multipart emailContent = new MimeMultipart();
		MimeBodyPart fileAttachment = new MimeBodyPart();
		fileAttachment.attachFile(mail.getAttachments());
		emailContent.addBodyPart(fileAttachment);
		message.setContent(emailContent);


	    emailSender.send(message);
	} catch (MessagingException e) {
	    e.printStackTrace();
	}catch(Exception e) {
	    e.printStackTrace(); 
	}
    }

}
