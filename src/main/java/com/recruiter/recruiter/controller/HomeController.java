package com.recruiter.recruiter.controller;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.service.JobPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    JobPostService jobPostService;

    @RequestMapping("/")
    private String index(Model model){
        List<JobPost> jobPosts = jobPostService.findFirst5ByStatusOrderByUpdatedAtDesc(true);
        model.addAttribute("listOfPosts", jobPosts);
        return "index";
    }

    @RequestMapping("/allJobs")
    private String allJob(Model model){
        List<JobPost> jobPosts = jobPostService.findAllByStatusOrderByUpdatedAtDesc(true);
        model.addAttribute("listOfPosts", jobPosts);
        return "uploaded-post";
    }
}
