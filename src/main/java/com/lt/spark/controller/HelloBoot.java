package com.lt.spark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by taoshiliu on 2018/2/20.
 */
@RestController
public class HelloBoot {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World Spring Boot ...";
    }
    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public ModelAndView firstDemo() {
        return new ModelAndView("test");
    }

}
