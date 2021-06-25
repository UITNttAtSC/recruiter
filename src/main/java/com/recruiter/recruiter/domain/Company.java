package com.recruiter.recruiter.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "companyId",nullable = false,updatable = false)
     private Long companyId;
     
	
     private String companyName;
     private String companyEmail;
     private String companyPassword;
     private String companyPhone;
     private String companyWebsite;
     private String companyType;
     private String noOfEmployee;

     @Column(columnDefinition = "text")
     private String companyAddress;

     @Transient
	private MultipartFile companyLogo;
    
     @Transient
	private MultipartFile companyFeaturePhoto;

     @Column(columnDefinition = "text")
     private String companyMission;

     @Column(columnDefinition = "text")
     private String companyVision;
     
     @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
     @JsonIgnore
     private List<JobPost> post;
     

    public List<JobPost> getPost() {
		return post;
	}

	public void setPost(List<JobPost> post) {
		this.post = post;
	}

	public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public MultipartFile getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(MultipartFile companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public MultipartFile getCompanyFeaturePhoto() {
        return companyFeaturePhoto;
    }

    public void setCompanyFeaturePhoto(MultipartFile companyFeaturePhoto) {
        this.companyFeaturePhoto = companyFeaturePhoto;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getNoOfEmployee() {
        return noOfEmployee;
    }

    public void setNoOfEmployee(String noOfEmployee) {
        this.noOfEmployee = noOfEmployee;
    }

    public String getCompanyMission() {
        return companyMission;
    }

    public void setCompanyMission(String companyMission) {
        this.companyMission = companyMission;
    }

    public String getCompanyVision() {
        return companyVision;
    }

    public void setCompanyVision(String companyVision) {
        this.companyVision = companyVision;
    }

    @Override
    public String toString() {
        return "Company [companyAddress=" + companyAddress + ", companyEmail=" + companyEmail + ", companyFeaturePhoto="
                + companyFeaturePhoto + ", companyId=" + companyId + ", companyLogo=" + companyLogo
                + ", companyMission=" + companyMission + ", companyName=" + companyName + ", companyPassword="
                + companyPassword + ", companyPhone=" + companyPhone + ", companyType=" + companyType
                + ", companyVision=" + companyVision + ", companyWebsite=" + companyWebsite + ", noOfEmployee="
                + noOfEmployee + "]";
    }

     
    
}
