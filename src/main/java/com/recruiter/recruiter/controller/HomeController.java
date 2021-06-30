package com.recruiter.recruiter.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.service.JobPostService;

@Controller
public class HomeController {

    @Autowired
    JobPostService jobPostService;

    @RequestMapping( {"/", "/index"} )
    private String index(Model model){
        List<JobPost> jobPosts = jobPostService.findFirst5ByStatusOrderByUpdatedAtDesc(true);
        
        Map<String, Long> counting = jobPostService.findAll().stream().collect(
                Collectors.groupingBy(JobPost::getJobCategory, Collectors.counting()));

	   Map<String, Long> finalMapDescendingOrder = new LinkedHashMap<>();
	   Map<String, String> mostAppliedJobLists = new LinkedHashMap<>();
	   
	      
	// Sort a map and add to finalMap
	  counting.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
			.forEachOrdered(e -> finalMapDescendingOrder.put(e.getKey(), e.getValue())) ;
	  
	  //Maximun 8 
	  finalMapDescendingOrder.entrySet().stream().limit(8)
		.forEachOrdered(e -> mostAppliedJobLists.put(e.getKey(), e.getValue()+" Job Posts")) ;
        
        
        model.addAttribute("listOfPosts", jobPosts);
        model.addAttribute("mostAppliedJobLists", mostAppliedJobLists);
        return "index";
    }
    
    
    
    @RequestMapping("/getPostsByCategory/{categoryName}")
    private String getPostsByCategory(@PathVariable("categoryName") String categoryName,Model model){
        List<JobPost> jobPosts = jobPostService.findByJobCategory(categoryName);
        
        
      	JobPost post = new JobPost();
      	post.setJobCategory(categoryName);
        // Company company = companyService.findByCompanyName(principal.getName());
        model.addAttribute("jobLocationsList", JobPostController.jobLocationsList);
        model.addAttribute("jobCategoriesList", JobPostController.jobCategoriesList);
        model.addAttribute("listOfPosts", jobPosts);
        model.addAttribute("noOfPosts", jobPosts.size());
        model.addAttribute("post", post);
        return "uploaded-post";
    }
    
    @RequestMapping("/getPostCountByCategory")
    private String getPossCountByCategory(Model model){
        
    	  Map<String, Long> counting = jobPostService.findAll().stream().collect(
                  Collectors.groupingBy(JobPost::getJobCategory, Collectors.counting()));

  	   Map<String, Long> finalMapDescendingOrder = new LinkedHashMap<>();
  	   Map<String, String> jobListCountByCategory = new LinkedHashMap<>();
  	   
  	      
  	// Sort a map and add to finalMap
  	  counting.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
  			.forEachOrdered(e -> finalMapDescendingOrder.put(e.getKey(), e.getValue())) ;
  	  
  	  //Maximun 8 
  	    finalMapDescendingOrder.entrySet().stream()
  		.forEachOrdered(e -> jobListCountByCategory.put(e.getKey(), e.getValue()+" Job Posts")) ;
          
          model.addAttribute("jobListCountByCategory", jobListCountByCategory);
          
          System.out.println(jobListCountByCategory);
  
        return "category_postCounts";
    }

    @RequestMapping("/allJobs")
    private String allJob(Model model){
        List<JobPost> jobPosts = jobPostService.findAllByStatusOrderByUpdatedAtDesc(true);
        model.addAttribute("listOfPosts", jobPosts);
        return "uploaded-post";
    }
}
