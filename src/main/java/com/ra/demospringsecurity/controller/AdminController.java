package com.ra.demospringsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = {"/",""})
    public String admin(){
        return "admin";
    }
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/check")
//    public String check(){return "admin";}
}
