package com.ryan.springcloud.controller;

import com.ryan.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String info_ok = paymentHystrixService.paymentInfo_OK(id);
        return info_ok;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id) {
        String info_timeout = paymentHystrixService.paymentInfo_TIMEOUT(id);
        return info_timeout;
    }


}
