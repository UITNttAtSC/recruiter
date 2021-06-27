package com.recruiter.recruiter.repository;

import java.util.List;

import com.recruiter.recruiter.domain.JobPost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobPostRepository extends PagingAndSortingRepository<JobPost, Long> {
    List<JobPost> findFirst5ByStatusOrderByUpdatedAt(boolean status);

    Page<JobPost> findAllByStatusOrderByUpdatedAt(boolean status, Pageable paging);
}
