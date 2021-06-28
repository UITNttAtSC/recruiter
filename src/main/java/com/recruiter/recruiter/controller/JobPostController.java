package com.recruiter.recruiter.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.domain.Payment;
import com.recruiter.recruiter.service.CompanyService;
import com.recruiter.recruiter.service.JobPostService;
import com.recruiter.recruiter.service.PaymentService;

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

    @Autowired
    PaymentService paymentService;

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
                                        add("Kaya");
    }};

    List<String> jobCategoriesList = new ArrayList<String>(){{
                                        add("Administration, business and management");
                                        add("Alternative therapies");
                                        add("Animals, land and environment");
                                        add("Computing and ICT");
                                        add("Construction and building");
                                        add("Design, arts and crafts");
                                        add("Education and training");
                                        add("Engineering");
                                        add("Facilities and property services");
                                        add("Financial services");
                                        add("Garage services");
                                        add("Hairdressing and beauty");
                                        add("Healthcare");
                                        add("Heritage, culture and libraries");
                                        add("Hospitality, catering and tourism");
                                        add("Languages");
                                        add("Legal and court services");
                                        add("Manufacturing and production");
                                        add("Performing arts and media");
                                        add("Print and publishing, marketing and advertising");
                                        add("Retail and customer services");
                                        add("Science, mathematics and statistics");
                                        add("Security, uniformed and protective services");
                                        add("Social sciences and religion");
                                        add("Social work and caring services");
                                        add("Sport and leisure");
                                        add("Transport, distribution and logistics");
    }};
        
    @RequestMapping("/postJob")
    private String postJob(Model model) {

        JobPost post = new JobPost();

        model.addAttribute("jobLocationsList", jobLocationsList);
        model.addAttribute("jobCategoriesList", jobCategoriesList);

        model.addAttribute("post", post);

        return "post_job";
    }
    
    @PostMapping("/addPost")
    private String addPost(@ModelAttribute("post") JobPost post, Principal principal) {

        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("gid");

        // Company company = companyService.findByCompanyName(principal.getName());

        Payment payment = paymentService.findByCompany(company);

        companyService.updateJobPost(post, company, payment);
        
        return "redirect:/viewPosts";
    }

    @RequestMapping("/viewPosts")
    private String viewPosts(Model model, Principal principal){

        Company company = companyService.findByCompanyName("gid");
        // Company company = companyService.findByCompanyName(principal.getName());
        model.addAttribute("listOfPosts", company.getPost());
        model.addAttribute("noOfPosts", company.getPost().size());
        return "uploaded-post";
    }

    @RequestMapping("/updatePost/{postId}")
    private String updatePost(@PathVariable("postId") Long postId, Model model){
        JobPost post = postService.findById(postId);

        model.addAttribute("jobLocationsList", jobLocationsList);
        model.addAttribute("jobCategoriesList", jobCategoriesList);

        model.addAttribute("post", post);
        return "post_job";
    }

    @RequestMapping("/deletePost/{postId}")
    private String deletePost(@PathVariable("postId") Long postId){
        postService.removeById(postId);
        return "redirect:/viewPosts";
    }

    @RequestMapping("/jobPostDetails/{jobPostId}")
    private String jobPostDetails(@PathVariable("jobPostId") Long jobPostId, Model model){
        JobPost jobPost = postService.findById(jobPostId);
        
        Long createdAt = jobPost.getCreatedAt().getTime();
        
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();

        long diffInMillies = Math.abs(currentTime - createdAt);
        long dateDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        model.addAttribute("dateDiff", dateDiff);
        model.addAttribute("jobPost", jobPost);

        return "job-post-detail";
    }
}
