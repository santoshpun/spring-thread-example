package com.santosh.springthreadexample.service;

import com.santosh.springthreadexample.model.User;
import com.santosh.springthreadexample.thread.UserTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class UserManager {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AsyncTaskExecutor taskExecutor;

    public void scanUserList() throws ExecutionException, InterruptedException {
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {

            UserTask userTask = applicationContext.getBean(UserTask.class, new User(i, "User" + i));

            Future<String> future = taskExecutor.submit(userTask);

            futureList.add(future);
        }

        for (Future<String> future : futureList) {
            log.info(Thread.currentThread().getName() + " : User Task COMPLETED : " + future.get());
        }
    }
}
