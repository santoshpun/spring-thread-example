package com.santosh.springthreadexample.service;

import com.santosh.springthreadexample.thread.OrderProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OrderManager {
    private TaskExecutor taskExecutor;
    private OrderProcessor orderProcessor;
    private MailSender mailSender;
    private UserManager userManager;

    @Autowired
    public OrderManager(TaskExecutor taskExecutor, OrderProcessor orderProcessor,
                        MailSender mailSender, UserManager userManager) {
        this.taskExecutor = taskExecutor;
        this.orderProcessor = orderProcessor;
        this.mailSender = mailSender;
        this.userManager = userManager;
    }

    @PostConstruct
    public void bootstrap() {
        // this.process();
        //this.performUserTask();
    }

    public void process() {
        List<String> orders = new ArrayList<>();
        orders.add("Collect package from seller");
        orders.add("Ship package to delivery unit");
        orders.add("Deliver package to customer");

        orderProcessor.setOrders(orders);

        this.taskExecutor.execute(orderProcessor);

        mailSender.sendMail();
    }

    public void performUserTask() {
        try {
            userManager.scanUserList();
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }
}
