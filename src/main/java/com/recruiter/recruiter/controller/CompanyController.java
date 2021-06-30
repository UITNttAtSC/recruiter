package com.recruiter.recruiter.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/companyInfo") 
public class CompanyController {
   
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/add")
	public String addCompany(Model model) {
		
		Company company=new Company();
		
		model.addAttribute("company", company);
		
		return "addCompany";	
		
	}
	
	@PostMapping("/add")
	public String addCompanyPost(@ModelAttribute("company") Company company,
			HttpServletRequest request,
			@ModelAttribute("companyLogo") MultipartFile companyLogo,
            @ModelAttribute("companyFeaturePhoto") MultipartFile companyFeaturePhoto
			) throws IOException {
				companyService.save(company);

				byte[] bytes = companyLogo.getBytes();

				String name = "logo"+ company.getCompanyId() + ".png";

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/companylogo/" + name)));

				stream.write(bytes);
                stream.close();

                byte[] bytes1 = companyFeaturePhoto.getBytes();

				String name1 = "featurePhoto"+ company.getCompanyId() + ".png";

				BufferedOutputStream stream1 = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/companyFeaturePhoto/" + name1)));

				stream1.write(bytes1);
				stream1.close();

				return "redirect:/book/bookList";
		
	}

}
