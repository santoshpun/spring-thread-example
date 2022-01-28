package com.santosh.springthreadexample.thread;

import com.santosh.springthreadexample.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserTask implements Callable<String> {
    private User user;

    public UserTask(User user) {
        this.user = user;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        printUserDetails();
        return user.getName();
    }

    public void printUserDetails() {
        log.info("==== User Details ====");
        log.info("Id : " + user.getId());
        log.info("Name : " + user.getName());
    }
}
