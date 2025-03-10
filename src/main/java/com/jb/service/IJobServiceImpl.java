package com.jb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jb.enums.Role;
import com.jb.exception.ResourceNotFoundException;
import com.jb.model.Job;
import com.jb.repository.IJobRepository;
import com.jb.repository.IUserRepository;

@Service
@Transactional
public class IJobServiceImpl implements IJobService {

    @Autowired
    private IJobRepository jobRepo;

    @Autowired
    private IUserRepository userRepo;

    @Override
    public Job postJob(Integer adminId, Job job) {
        userRepo.findById(adminId)
                .filter(user -> Role.ADMIN.equals(user.getRole()))
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + adminId + " not found or is not an admin."));
        return jobRepo.save(job);
    }

    @Override
    public Job updateJob(Integer id, Job updatedJob) {
        Job existingJob = jobRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No job found with ID: " + id));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setCompany(updatedJob.getCompany());
        existingJob.setLocation(updatedJob.getLocation());

        return jobRepo.save(existingJob);
    }

    @Override
    public String deleteJob(Integer id) {
        if (!jobRepo.existsById(id)) {
            throw new ResourceNotFoundException("No job found with ID: " + id);
        }
        jobRepo.deleteById(id);
        return "Job deleted successfully with ID: " + id;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public List<Job> findByCity(String location) {
        return jobRepo.findByCity(location);
    }

    @Override
    public List<Job> findByCompany(String company) {
        return jobRepo.findByCompany(company);
    }

    @Override
    public List<Job> findBySalary(Double salary) {
        return jobRepo.findBySalary(salary);
    }

    @Override
    public List<Job> findByTitle(String title) {
        return jobRepo.findByTitle(title);
    }

    @Override
    public Optional<Job> findById(Integer id) {
        return jobRepo.findById(id);
    }
}
