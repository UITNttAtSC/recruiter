package com.recruiter.recruiter.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruiter.recruiter.domain.JobApply;
import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.domain.ReplyEmail;
import com.recruiter.recruiter.domain.User;
import com.recruiter.recruiter.service.JobApplyService;
import com.recruiter.recruiter.service.JobPostService;
import com.recruiter.recruiter.service.UserService;
import com.recruiter.recruiter.utility.MailConstructor;

@Controller
public class JobApplyController {

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	JobPostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private JobApplyService jobApplyService;

	@Autowired
	private JobPostService jobPostServie;

	@RequestMapping("/jobApply/{postId}")
	private String jobApply(@PathVariable("postId") Long postId, Model model, Principal principal) {
		User user = userService.findByUsername("HH");
		model.addAttribute("user", user);
		model.addAttribute("jobApply", new JobApply());
		model.addAttribute("post_id", postId);

		return "job_apply";
	}

	@RequestMapping("/jobApplyUsers/{postId}")
	private String jobApplyUsers(@PathVariable("postId") Long postId, Model model, Principal principal) {
		JobPost jobPost = jobPostServie.findById(postId);
		List<User> userList = jobApplyService.findUserByJobPostId(jobPost);

		model.addAttribute("userList", userList);
		model.addAttribute("post_id", postId);

		return "user_list";
	}

	@RequestMapping("/userApplyJobs/{userId}")
	private String userApplyJobs(@PathVariable("userId") Long userId, Model model, Principal principal) {
		User user = userService.findById(userId);
		List<JobPost> jobList = jobApplyService.findJobPostByUserId(user);

		model.addAttribute("userId", userId);
		model.addAttribute("listOfPosts", jobList);
		model.addAttribute("noOfPosts", jobList.size());
		return "uploaded-post";

	}

	@RequestMapping("/applyJobEmailFormat2")
	private String applyJobEmailFormat(Model model) {
		return "interview_invitation_email_format";
	}

	@PostMapping("/jobApply/{postId}")
	private String jobApply(HttpServletRequest request, @PathVariable("postId") Long postId,
			@ModelAttribute("userId") Long userId,
			@ModelAttribute("jobApply") JobApply jobApply, Model model

	) throws IOException, InterruptedException {

		JobPost jobPost = jobPostServie.findById(postId);
		User user = userService.findById(userId);

		System.out.println(user);
		jobApply.setJobPost(jobPost);
		jobApply.setUser(user);
		
		JobApply alreadyApply = jobApplyService.alreadyApply(user, jobPost);

		if(alreadyApply == null) {
			jobApplyService.save(jobApply);
		}	

		/*
		 * if (jobApply.getAttachFiles().length > 1) {
		 * 
		 * BufferedOutputStream stream = null;
		 * 
		 * for (MultipartFile file : jobApply.getAttachFiles()) {
		 * 
		 * // jobApply.getAttachFiles()..add(file.getOriginalFilename());
		 * 
		 * stream = new BufferedOutputStream(new FileOutputStream( new
		 * File("src/main/resources/static/applyfiles/" + file.getOriginalFilename())));
		 * 
		 * stream.write(file.getBytes());
		 * 
		 * stream.close();
		 * 
		 * } }
		 * 
		 * String userDetailLink = "";
		 * mailSender.send(mailConstructor.applyJobEmail(request.getLocale(),
		 * userDetailLink,jobApply));
		 * 
		 * File applyFiles = new File("src/main/resources/static/applyfiles/");
		 * 
		 * if (applyFiles.length() > 0) {
		 * 
		 * for (File file : applyFiles.listFiles()) {
		 * 
		 * file.delete();
		 * 
		 * }
		 * 
		 * }
		 */
		
		model.addAttribute("emailSuccess", true);
        
		return "redirect:/viewPosts";

	}
	
	
	@RequestMapping("/interviewInvitaionToUser/{postId}/{userId}")
	private String replyEmailToUser(@PathVariable("postId") Long postId,
			@PathVariable("userId") Long userId,
			Model model, Principal principal) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("replyEmail", new ReplyEmail());
		model.addAttribute("postId", postId);
		return "interview_invitation_to_user";
	}
	
	
	@PostMapping("/interviewInvitaionToUser/{postId}/{userId}")
	private String interviewInvitaionToUser(HttpServletRequest request, @PathVariable("postId") Long postId,
			@PathVariable("userId") Long userId,
			@ModelAttribute("replyEmail") ReplyEmail replyEmail, Model model

	) throws IOException, InterruptedException {

		JobPost post = jobPostServie.findById(postId);
		User user = userService.findById(userId);
		
		
		//June 30, at 9 a.m
		
		 LocalDateTime time = LocalDateTime.parse(replyEmail.getInterviewTime());
		 DateTimeFormatter dateTimeformat = DateTimeFormatter.ofPattern("hh:mm a dd E MMM uuuu");
		 
	     
	     String interviewTimeString = dateTimeformat.format(time);
	     
	     replyEmail.setInterviewTime(interviewTimeString);
	     
	     String userDetailLink = "";
		 mailSender.send(mailConstructor.interviewInvitationEmail(request.getLocale(), userDetailLink, replyEmail, user, post));
	     
		System.out.println(interviewTimeString);
		System.out.println(replyEmail.getEmailSubject());
		System.out.println(replyEmail.getInterviewLocation());
		System.out.println(replyEmail.getEmailContent1());
		System.out.println(replyEmail.getEmailContent2());
		
		model.addAttribute("emailSuccess", true);
        
		return "redirect:/viewPosts";

	}
	
	

}