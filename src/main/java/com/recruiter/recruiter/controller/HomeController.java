package com.recruiter.recruiter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/postJob")
    private String postJob() {
        return "post_job";
    }
}
