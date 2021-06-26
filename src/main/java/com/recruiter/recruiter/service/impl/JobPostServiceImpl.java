package com.recruiter.recruiter.service.impl;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;
import com.recruiter.recruiter.repository.JobPostRepository;
import com.recruiter.recruiter.service.JobPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPostServiceImpl implements JobPostService{

    @Autowired
    private JobPostRepository postRepository;

    @Override
    public List<JobPost> findAll() {
        return (List<JobPost>) postRepository.findAll();
    }

    @Override
    public JobPost save(JobPost post) {
        return postRepository.save(post);
    }

    @Override
    public JobPost findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<JobPost> findFirst5ByStatusOrderByUpdatedAtDesc(boolean status) {
        return postRepository.findFirst5ByStatusOrderByUpdatedAtDesc(status);
    }

    @Override
    public List<JobPost> findAllByStatusOrderByUpdatedAtDesc(boolean status) {
        return postRepository.findAllByStatusOrderByUpdatedAtDesc(status);
    }
    
}
