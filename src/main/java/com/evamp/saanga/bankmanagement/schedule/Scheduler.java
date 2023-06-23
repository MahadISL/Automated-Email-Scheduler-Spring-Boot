package com.evamp.saanga.bankmanagement.schedule;

import com.evamp.saanga.bankmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class Scheduler {

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 * 16 * * ?")
    public void scheduleTask() throws IOException, MessagingException {

        SimpleDateFormat dateFormat = new SimpleDateFormat();

        String strDate = dateFormat.format(new Date());

        System.out.println("Cron job scheduler: Job running at - " + strDate);
        userService.automatedEmailSchedule();

    }
}
