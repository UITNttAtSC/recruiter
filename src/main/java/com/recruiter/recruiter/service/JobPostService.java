package com.recruiter.recruiter.service;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;

public interface JobPostService {
    List<JobPost> findAll();

    List<JobPost> findFirst5ByStatusOrderByUpdatedAtDesc(boolean status);

    List<JobPost> findAllByStatusOrderByUpdatedAtDesc(boolean status);

    JobPost save(JobPost post);

    JobPost findById(Long id);

    void removeById(Long id);
    
    List<JobPost> findByJobCategory(String jobCategory);
}
