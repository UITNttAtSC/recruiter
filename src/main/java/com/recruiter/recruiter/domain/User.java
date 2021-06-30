package com.recruiter.recruiter.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false,updatable = false)
	private Long id;
	private String username;
	private String password;

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	private Company company;
	
	@Column(name = "email",nullable = false,updatable = false)
	private String email;
	
	private String phone;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<JobApply> jobApplyList;
	
	
	
	public List<JobApply> getJobApplyList() {
		return jobApplyList;
	}

	public void setJobApplyList(List<JobApply> jobApplyList) {
		this.jobApplyList = jobApplyList;
	}

	public String getUsername() {
		return username;
	}

	private boolean enabled = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

    /**
     * @return Company return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

}
