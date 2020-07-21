package com.victor.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender getJavaMailSender() {
	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	/*
	 * mailSender.setPort(Integer.parseInt(String.valueOf(environment.
	 * getRequiredProperty("spring.mail.properties.mail.smtp.port"))));
	 * mailSender.setUsername(environment.getRequiredProperty("spring.mail.username"
	 * ));
	 * mailSender.setPassword(environment.getRequiredProperty("spring.mail.password"
	 * )); mailSender.setHost(environment.getRequiredProperty("spring.mail.host"));
	 */
	mailSender.setHost("smtp.gmail.com");
	mailSender.setPort(587);
	mailSender.setUsername("mydevemail2020@gmail.com");
	mailSender.setPassword("mydevemail20201#");
	Properties props = mailSender.getJavaMailProperties();
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
	return mailSender;
    }

}
