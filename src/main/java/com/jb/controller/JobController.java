package com.jb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jb.model.Job;
import com.jb.service.IJobService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private IJobService jobService;

    @GetMapping("/view-jobs")
    public ResponseEntity<List<Job>> findAllJobs() {
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @PostMapping("/post-job")
    public ResponseEntity<Job> postJob(@RequestParam Integer adminId, @RequestBody Job job) {
        return new ResponseEntity<>(jobService.postJob(adminId, job), HttpStatus.CREATED);
    }

    @PutMapping("/update-job/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer id, @RequestBody Job job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    @DeleteMapping("/delete-job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Integer id) {
        return ResponseEntity.ok(jobService.deleteJob(id));
    }

    @GetMapping("/view-job/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Integer id) {
        Optional<Job> job = jobService.findById(id);
        return job.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                 .body(null));
    }

    @GetMapping("/view-jobs/city")
    public ResponseEntity<List<Job>> findJobsByCity(@RequestParam String city) {
        return ResponseEntity.ok(jobService.findByCity(city));
    }

    @GetMapping("/view-jobs/company")
    public ResponseEntity<List<Job>> findJobsByCompany(@RequestParam String company) {
        return ResponseEntity.ok(jobService.findByCompany(company));
    }

    @GetMapping("/view-jobs/title")
    public ResponseEntity<List<Job>> findJobsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(jobService.findByTitle(title));
    }

    @GetMapping("/view-jobs/salary")
    public ResponseEntity<List<Job>> findJobsBySalary(@RequestParam Double salary) {
        return ResponseEntity.ok(jobService.findBySalary(salary));
    }
}
