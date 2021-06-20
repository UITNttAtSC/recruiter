package com.recruiter.recruiter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    private String companyName;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobPost> post = new ArrayList<JobPost>();

    /**
     * @return Long return the companyId
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * @return String return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return List<JobPost> return the post
     */
    public List<JobPost> getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(List<JobPost> post) {
        this.post = post;
    }

}
