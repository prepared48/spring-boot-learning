package com.example.demo.log.sub1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: prepared
 * @Date: 2021/4/14 15:04
 */
@Controller
@RequestMapping("/test2")
public class Test2Controller {

    @RequestMapping("/test2")
    public String test1() {
        System.out.println("执行test2-test2");
        return "success-test2";
    }
}
