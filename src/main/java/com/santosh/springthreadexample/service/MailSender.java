package com.santosh.springthreadexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class MailSender {

    @Async
    @Scheduled(fixedRate = 5000)
    public void sendMail() {
        log.info("Sending mail... ");

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        String str = null;
//        str.toString();

        log.info("Mail sent !!");
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void processEvery5Seconds() {
        log.info("Method executed at every 5 seconds. Current time is : " + new Date());
    }
}
