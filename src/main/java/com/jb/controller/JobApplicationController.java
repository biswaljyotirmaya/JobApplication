package com.jb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.enums.Status;
import com.jb.model.JobApplication;
import com.jb.service.IJobApplicationService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/job-application")
public class JobApplicationController {

	@Autowired
	private IJobApplicationService jobApplicationService;

	@PostMapping("/user/apply")
	public ResponseEntity<String> applyForJob(@RequestParam Integer userId, @RequestParam Integer jobId) {
		return new ResponseEntity<>(jobApplicationService.applyForJob(userId, jobId), HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<JobApplication>> getUserApplications(@PathVariable Integer userId) {
		return new ResponseEntity<>(jobApplicationService.getApplicationsByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/user/status/{applicationId}")
	public ResponseEntity<Status> checkApplicationStatus(@PathVariable Integer applicationId) {
		return new ResponseEntity<>(jobApplicationService.getApplicationStatus(applicationId), HttpStatus.OK);
	}

	@GetMapping("/admin/all")
	public ResponseEntity<List<JobApplication>> getAllApplications() {
		return new ResponseEntity<>(jobApplicationService.getAllApplications(), HttpStatus.OK);
	}

	@GetMapping("/admin/job/{jobId}")
	public ResponseEntity<List<JobApplication>> getApplicationsForJob(@PathVariable Integer jobId) {
		return new ResponseEntity<>(jobApplicationService.getApplicationsByJob(jobId), HttpStatus.OK);
	}

	@PutMapping("/admin/update-status/{applicationId}")
	public ResponseEntity<String> updateApplicationStatus(@PathVariable Integer applicationId,
			@RequestParam Status status) {
		return new ResponseEntity<>(jobApplicationService.updateApplicationStatus(applicationId, status),
				HttpStatus.OK);
	}

	@DeleteMapping("/admin/delete/{applicationId}")
	public ResponseEntity<String> deleteApplication(@PathVariable Integer applicationId) {
		return new ResponseEntity<>(jobApplicationService.deleteApplication(applicationId), HttpStatus.OK);
	}
}
