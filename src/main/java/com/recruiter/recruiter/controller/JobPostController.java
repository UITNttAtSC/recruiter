package com.recruiter.recruiter.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.service.CompanyService;
import com.recruiter.recruiter.service.JobPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobPostController {

    @Autowired
    JobPostService postService;

    @Autowired
    CompanyService companyService;

    List<String> jobLocationsList = new ArrayList<String>(){{
                                        add("Yangon");
                                        add("Mandalay");
                                        add("Nay Pyi Thaw");
                                        add("Mon");
                                        add("Shan");
                                        add("Bago");
                                        add("Sagaing");
                                        add("Kachin");
                                        add("Ayeyarwady");
                                        add("Rakhine");
                                        add("Magway");
                                        add("Tanintharyi");
                                        add("Kayin");
                                        add("Kaya");}};
        
    @RequestMapping("/postJob")
    private String postJob(Model model) {

        JobPost post = new JobPost();

        model.addAttribute("jobLocationsList", jobLocationsList);

        model.addAttribute("post", post);

        return "post_job";
    }
    
    @PostMapping("/addPost")
    private String addPost(@ModelAttribute("post") JobPost post, Principal principal) {

        Company company = new Company();
        company.setCompanyId(2L);
        company.setCompanyName("GIC");

        // Company company = companyService.findByCompanyName(principal.getName());

        companyService.updateJobPost(post, company);
        
        return "redirect:/viewPosts";
    }

    @RequestMapping("/viewPosts")
    private String viewPosts(Model model, Principal principal){

        Company company = companyService.findByCompanyName("GIC");
        // Company company = companyService.findByCompanyName(principal.getName());
        model.addAttribute("listOfPosts", company.getPost());
        model.addAttribute("noOfPosts", company.getPost().size());
        return "uploaded-post";
    }

    @RequestMapping("/updatePost/{postId}")
    private String updatePost(@PathVariable("postId") Long postId, Model model){
        JobPost post = postService.findById(postId);

        model.addAttribute("jobLocationsList", jobLocationsList);

        model.addAttribute("post", post);
        return "post_job";
    }

    @RequestMapping("/deletePost/{postId}")
    private String deletePost(@PathVariable("postId") Long postId){
        postService.removeById(postId);
        return "redirect:/viewPosts";
    }
}
