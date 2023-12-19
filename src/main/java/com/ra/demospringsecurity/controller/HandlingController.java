package com.ra.demospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HandlingController {
    @RequestMapping("/403")
    public String demo(){
        return "403";
    }
}
