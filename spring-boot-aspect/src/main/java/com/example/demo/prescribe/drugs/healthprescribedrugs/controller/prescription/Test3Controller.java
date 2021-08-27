package com.example.demo.prescribe.drugs.healthprescribedrugs.controller.prescription;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhongshibo
 * @Date: 2021/4/14 15:04
 */
@Controller
@RequestMapping("/test3")
public class Test3Controller {

    @RequestMapping("/test3")
    public String test1() {
        System.out.println("执行test3-test3");
        System.out.println("执行com.example.demo.prescribe.drugs.healthprescribedrugs.controller.prescription.test3-test3");
        return "success-test3";
    }
}
