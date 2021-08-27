package com.example.demo.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: prepared
 * @Date: 2021/4/14 15:04
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public void test1() {
    }
}
