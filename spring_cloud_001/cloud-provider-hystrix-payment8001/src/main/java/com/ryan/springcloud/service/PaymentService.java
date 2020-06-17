package com.ryan.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "thread pool:" + Thread.currentThread().getName()+"OK"+id;
    }

    public String paymentInfo_TIMEOUT(Integer id) {
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        return "thread pool:" + Thread.currentThread().getName()+"TIMEOUT"+id;
    }
}
