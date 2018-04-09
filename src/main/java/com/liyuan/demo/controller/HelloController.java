package com.liyuan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 11:03 2018/2/8
 * @Modified By:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
