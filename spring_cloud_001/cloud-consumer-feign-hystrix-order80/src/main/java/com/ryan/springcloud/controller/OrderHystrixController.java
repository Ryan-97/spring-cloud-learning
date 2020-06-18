package com.ryan.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ryan.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
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
//    @HystrixCommand(fallbackMethod = "paymentInfo_TImeOutHandler", commandProperties ={
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //3秒钟以内就是正常的业务逻辑
//    })
    @HystrixCommand
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id) {
        int age = 10/0;
        String info_timeout = paymentHystrixService.paymentInfo_TIMEOUT(id);
        return info_timeout;
    }

    public String paymentInfo_TImeOutHandler(Integer id) {
        return "I am consumer";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试";
    }


}
