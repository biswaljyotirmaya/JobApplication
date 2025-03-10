package com.jb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.enums.Status;
import com.jb.exception.ResourceNotFoundException;
import com.jb.model.Job;
import com.jb.model.JobApplication;
import com.jb.model.User;
import com.jb.repository.IJobApplicationRepository;
import com.jb.repository.IJobRepository;
import com.jb.repository.IUserRepository;

@Service
public class IJobApplicationServiceImpl implements IJobApplicationService {

    @Autowired
    private IJobApplicationRepository jobApplicationRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IJobRepository jobRepo;

    @Override
    public String applyForJob(Integer userId, Integer jobId) {
        User user = userRepo.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));
        Job job = jobRepo.findById(jobId)
                         .orElseThrow(() -> new ResourceNotFoundException("Job with ID " + jobId + " not found."));

        JobApplication jobApplication = new JobApplication();
        jobApplication.setUser(user);
        jobApplication.setJob(job);
        jobApplication.setStatus(Status.PENDING);
        jobApplicationRepo.save(jobApplication);

        return "Application submitted successfully.";
    }

    @Override
    public List<JobApplication> getApplicationsByUser(Integer userId) {
        return jobApplicationRepo.findByUserId(userId);
    }

    @Override
    public Status getApplicationStatus(Integer applicationId) {
        return jobApplicationRepo.findById(applicationId)
                .map(JobApplication::getStatus)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + applicationId + " not found."));
    }

    @Override
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepo.findAll();
    }

    @Override
    public List<JobApplication> getApplicationsByJob(Integer jobId) {
        return jobApplicationRepo.findByJobId(jobId);
    }

    @Override
    public String updateApplicationStatus(Integer applicationId, Status status) {
        JobApplication application = jobApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + applicationId + " not found."));

        application.setStatus(status);
        jobApplicationRepo.save(application);
        return "Application status updated successfully.";
    }

    @Override
    public String deleteApplication(Integer applicationId) {
        if (!jobApplicationRepo.existsById(applicationId)) {
            throw new ResourceNotFoundException("Application with ID " + applicationId + " not found.");
        }

        jobApplicationRepo.deleteById(applicationId);
        return "Application deleted successfully.";
    }
}
