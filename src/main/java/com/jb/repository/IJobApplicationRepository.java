package com.jb.repository;

import com.jb.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    @Query("SELECT ja FROM JobApplication ja WHERE ja.user.id = :userId")
    List<JobApplication> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT ja FROM JobApplication ja WHERE ja.job.id = :jobId")
    List<JobApplication> findByJobId(@Param("jobId") Integer jobId);
}
