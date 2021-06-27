package com.recruiter.recruiter.controller;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.service.JobPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    JobPostService jobPostService;

    @RequestMapping("/")
    private String index(Model model){
        List<JobPost> jobPosts = jobPostService.findFirst5ByStatusOrderByUpdatedAt(true);
        model.addAttribute("listOfPosts", jobPosts);
        return "index";
    }

    @RequestMapping("/allJobs")
    private String allJobs(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        Page<JobPost> jobPosts = jobPostService.findAllByStatusOrderByUpdatedAt(pageNo-1, pageSize, true);
        model.addAttribute("listOfPosts", jobPosts.getContent());
        model.addAttribute("noOfPosts", jobPosts.getContent().size());
        model.addAttribute("totalPages", jobPosts.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("hasNext", jobPosts.hasNext());
        model.addAttribute("hasPrevious", jobPosts.hasPrevious());
        return "uploaded-post";
    }
}
