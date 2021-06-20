package com.recruiter.recruiter.service;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;

public interface JobPostService {
    List<JobPost> findAll();

    JobPost save(JobPost post);

    JobPost findById(Long id);

    void removeById(Long id);
}
