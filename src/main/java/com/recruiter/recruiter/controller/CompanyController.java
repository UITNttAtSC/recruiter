package com.recruiter.recruiter.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import com.recruiter.recruiter.domain.Company;
import com.recruiter.recruiter.domain.User;
import com.recruiter.recruiter.service.CompanyService;
import com.recruiter.recruiter.service.UserService;

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
  
  
    @Autowired
    UserService userService;
     
    @RequestMapping("/newCompany")
    private String createCompanyProfile(Model model, Principal principal){
        // User user = userService.findByUsername(principal.getName());
        User user = userService.findByUsername("gid");
        Company company=new Company();
        model.addAttribute("company", company);
        model.addAttribute("user", user);
        model.addAttribute("action", "create");
        return "company_register";
    }

    @RequestMapping("/companyAccount")
    private String companyAccount(Model model, Principal principal){
        // User user = userService.findByUsername(principal.getName());
        User user = userService.findByUsername("gid");
        Company company = companyService.findByUser_Id(user.getId());
        model.addAttribute("company", company);
        model.addAttribute("user", user);
        model.addAttribute("action", "update");
        return "company_register";
    }
    
    @PostMapping("/newCompany")
    public String newCompanyPost(@ModelAttribute("company") Company company,
    @ModelAttribute("user") User currentUser,
    @ModelAttribute("password") String password,
    @ModelAttribute("action") String action,
    @ModelAttribute("companyLogo") MultipartFile companyLogo,
    @ModelAttribute("companyFeaturePhotos") List<MultipartFile> companyFeaturePhotos,
    Model model)
    throws IOException{
        
        byte[] bytes = companyLogo.getBytes();
        
        String name = "logo"+ company.getCompanyName() + ".png";
        
        BufferedOutputStream stream = new BufferedOutputStream(
        new FileOutputStream(new File("src/main/resources/static/image/companyLogo/" + name)));
        
        stream.write(bytes);
        
        int i = 0;
        for(MultipartFile companyFeaturePhoto : companyFeaturePhotos){
            String fileName = "featurePhoto"+ company.getCompanyName() + i + ".png";
            byte[] bytes1 = companyFeaturePhoto.getBytes();
            stream = new BufferedOutputStream(
                new FileOutputStream(new File("src/main/resources/static/image/companyFeaturePhoto/" + fileName)));
            i++;
            stream.write(bytes1);
        }
        stream.close();
        
        if (companyService.findByCompanyName(company.getCompanyName()) != null && action.equals("create")) {
            model.addAttribute("companyNameExists", true);
            
            return "company_register";
        }
        
        User user = userService.findByEmail(currentUser.getEmail());
        user.setPassword(password);

        Company currentCompany = companyService.findByUser_Id(user.getId());
        currentCompany.setCompanyName(company.getCompanyName());
        currentCompany.setCompanyPhone(company.getCompanyPhone());
        currentCompany.setCompanyWebsite(company.getCompanyWebsite());
        currentCompany.setCompanyType(company.getCompanyType());
        currentCompany.setNoOfEmployee(company.getNoOfEmployee());
        currentCompany.setCompanyAddress(company.getCompanyAddress());
        currentCompany.setCompanyMission(company.getCompanyMission());
        currentCompany.setCompanyVision(company.getCompanyVision());
        // user.setPassword(SecurityUtility.passwordEncoder().encode(password));
        companyService.save(user, currentCompany);
        return "index";
    }
}
