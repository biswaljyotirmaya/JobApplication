package com.jb.service;

import com.jb.enums.Status;
import com.jb.model.JobApplication;
import java.util.List;

public interface IJobApplicationService {

	String applyForJob(Integer userId, Integer jobId);

	List<JobApplication> getApplicationsByUser(Integer userId);

	Status getApplicationStatus(Integer applicationId);

	List<JobApplication> getAllApplications();

	List<JobApplication> getApplicationsByJob(Integer jobId);

	String deleteApplication(Integer applicationId);

	String updateApplicationStatus(Integer applicationId, Status status);
}
