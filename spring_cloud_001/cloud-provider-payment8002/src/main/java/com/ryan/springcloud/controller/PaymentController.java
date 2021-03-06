package com.ryan.springcloud.controller;

import com.ryan.springcloud.entities.CommonResult;
import com.ryan.springcloud.entities.Payment;
import com.ryan.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result>0)
        {
            return new CommonResult(200, "success"+serverPort, result);
        }else {
            return new CommonResult(404, "fail", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment=  paymentService.getPaymentById(id);
        LOG.info("result:"+payment);
//        Log.info("result:"+payment);

        if (payment != null)
        {
            return new CommonResult(200, "success"+serverPort, payment);
        }else {
            return new CommonResult(404, "fail", null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
