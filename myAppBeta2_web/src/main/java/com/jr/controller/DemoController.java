package com.jr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jql
 * @create 2017-11-01 10:59
 * @desc 项目controller层的demo
 **/
@Controller
public class DemoController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("demo")
    public String toDemoPage(){
        return "demo";
    }

}
