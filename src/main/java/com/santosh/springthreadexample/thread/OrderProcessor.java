package com.santosh.springthreadexample.thread;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderProcessor implements Runnable {
    private List<String> orders;

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {

        for (String order : orders) {

            System.out.println("Status : " + order + " , " + Thread.currentThread().getName());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
