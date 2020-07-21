package com.victor.scheduler.services;

import com.victor.scheduler.config.FileStorageProperties;
import com.victor.scheduler.config.Setting;
import com.victor.scheduler.dtos.MailDto;
import com.victor.scheduler.repositories.RecipientRepository;
import com.victor.scheduler.repositories.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

/**
 * Alternative version for DynamicScheduler
 * This one should support everything the basic dynamic scheduler does,
 * and on top of it, you can cancel and re-activate the scheduler.
 */
@Service
@EnableScheduling
public class DynamicScheduler implements SchedulingConfigurer {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicScheduler.class);

    ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    private MailService mailService;

    @Autowired
    private RecipientRepository recipientRepository;


    ScheduledFuture future1;
    ScheduledFuture future2;
    ScheduledFuture future3;
    Map<ScheduledFuture, Boolean> futureMap = new HashMap<>();

    @Bean
    public TaskScheduler poolScheduler2() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

    // We can have multiple tasks inside the same registrar as we can see below.
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (taskRegistrar.getScheduler() == null) {
            taskRegistrar.setScheduler(poolScheduler2());
        }
//
//        if (future1 == null || (future1.isCancelled() && futureMap.get(future1) == true)) {
//            future1 = taskRegistrar.getScheduler().schedule(() -> scheduleFixed(5), t -> {
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = t.lastActualExecutionTime();
//                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.SECOND, 5);
//                return nextExecutionTime.getTime();
//            });
//        }

//        if (future2 == null || (future2.isCancelled() && futureMap.get(future2) == true)) {
//            future2 = taskRegistrar.getScheduler().schedule(() -> scheduleFixed(8), t -> {
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = t.lastActualExecutionTime();
//                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.SECOND, 8);
//                return nextExecutionTime.getTime();
//            });
//        }

        // Or cron way, you can also get the expression from DB or somewhere else just like we did in DynamicScheduler service.
        if (future3 == null || (future3.isCancelled() && futureMap.get(future3) == true)) {
            CronTrigger croneTrigger = new CronTrigger(reportRepository.findById(1L).get().getScheduleExpression(), TimeZone.getDefault());
            future3 = taskRegistrar.getScheduler().schedule(() -> scheduleCron(reportRepository.findById(1L).get().getScheduleExpression()), croneTrigger);
        }
    }

    public void scheduleFixed(int frequency) {
        LOGGER.info("scheduleFixed: Next execution time of this will always be {} seconds", frequency);
    }

    // This is the main method I am sending the scheduled report as an attached file
    // Only reason this method gets the cron as parameter is for debug purposes.
    public void scheduleCron(String cron) {
        LOGGER.info("scheduleCron: Next execution time of this taken from cron expression -> {}", cron);
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + File.separator + reportRepository.getFileName())
                .toAbsolutePath().normalize();
        String[] allEmail = recipientRepository.allEmail();
        MailDto mail = MailDto.builder().mailFrom(Setting.FROM_EMAIL).mailTo(allEmail).mailSubject("Scheduled Report")
                .mailTemplateName("report-email-template.ftl")
                .attachments(fileStorageLocation.toFile())
                .build();
        Map<String, Object> model = new HashMap<String, Object>();
        // send mail
         mailService.sendSimpleMessage(mail, model);
    }


    public void cancelFuture(boolean mayInterruptIfRunning, ScheduledFuture future) {
        LOGGER.info("Cancelling a future");
        future.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
        futureMap.put(future, false);
    }

    public void activateFuture(ScheduledFuture future) {
        LOGGER.info("Re-Activating a future");
        futureMap.put(future, true);
        configureTasks(scheduledTaskRegistrar);
    }

    public void cancelAll() {
        cancelFuture(true, future1);
        cancelFuture(true, future2);
        cancelFuture(true, future3);
    }

    public void activateAll() {
        activateFuture(future1);
        activateFuture(future2);
        activateFuture(future3);
    }

}
