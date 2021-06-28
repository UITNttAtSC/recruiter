package com.recruiter.recruiter.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class JobPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String jobTitle;

    private String jobCategory;

    private String jobLevel;

    private String jobType;

    private Integer minSalary;

    private Integer maxSalary;

    private String ageLimit;

    private Integer employeeLimit;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private String jobHour;

    private String jobDay;

    private String jobLocation;

    @Column(columnDefinition = "text")
    private String jobDescription;

    @Column(columnDefinition = "text")
    private String jobRequirement;

    @Column(columnDefinition = "text")
    private String jobBenefit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    private boolean status = false;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Payment payment;

    /**
     * @return Long return the postId
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * @return String return the jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return String return the jobLevel
     */
    public String getJobLevel() {
        return jobLevel;
    }

    /**
     * @param jobLevel the jobLevel to set
     */
    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    /**
     * @return String return the jobType
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * @param jobType the jobType to set
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * @return Integer return the minSalary
     */
    public Integer getMinSalary() {
        return minSalary;
    }

    /**
     * @param minSalary the minSalary to set
     */
    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    /**
     * @return Integer return the maxSalary
     */
    public Integer getMaxSalary() {
        return maxSalary;
    }

    /**
     * @param maxSalary the maxSalary to set
     */
    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    /**
     * @return String return the ageLimit
     */
    public String getAgeLimit() {
        return ageLimit;
    }

    /**
     * @param ageLimit the ageLimit to set
     */
    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    /**
     * @return Integer return the employeeLimit
     */
    public Integer getEmployeeLimit() {
        return employeeLimit;
    }

    /**
     * @param employeeLimit the employeeLimit to set
     */
    public void setEmployeeLimit(Integer employeeLimit) {
        this.employeeLimit = employeeLimit;
    }

    /**
     * @return Date return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return String return the jobHour
     */
    public String getJobHour() {
        return jobHour;
    }

    /**
     * @param jobHour the jobHour to set
     */
    public void setJobHour(String jobHour) {
        this.jobHour = jobHour;
    }

    /**
     * @return String return the jobDay
     */
    public String getJobDay() {
        return jobDay;
    }

    /**
     * @param jobDay the jobDay to set
     */
    public void setJobDay(String jobDay) {
        this.jobDay = jobDay;
    }

    /**
     * @return String return the jobLocation
     */
    public String getJobLocation() {
        return jobLocation;
    }

    /**
     * @param jobLocation the jobLocation to set
     */
    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    /**
     * @return String return the jobDescription
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * @param jobDescription the jobDescription to set
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @return String return the jobRequirement
     */
    public String getJobRequirement() {
        return jobRequirement;
    }

    /**
     * @param jobRequirement the jobRequirement to set
     */
    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    /**
     * @return String return the jobBenefit
     */
    public String getJobBenefit() {
        return jobBenefit;
    }

    /**
     * @param jobBenefit the jobBenefit to set
     */
    public void setJobBenefit(String jobBenefit) {
        this.jobBenefit = jobBenefit;
    }

    /**
     * @return Date return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return Date return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return boolean return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
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

    /**
     * @return String return the jobCategory
     */
    public String getJobCategory() {
        return jobCategory;
    }

    /**
     * @param jobCategory the jobCategory to set
     */
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }


    /**
     * @return Payment return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
