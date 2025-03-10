package com.jb.service;

import java.util.List;
import java.util.Optional;

import com.jb.model.Job;

public interface IJobService {

	public Job postJob(Integer adminId, Job job);

	public Job updateJob(Integer id, Job job);

	public String deleteJob(Integer id);;

	public List<Job> findAllJobs();

	public List<Job> findByCity(String location);

	public List<Job> findBySalary(Double salary);

	public List<Job> findByTitle(String title);

	public List<Job> findByCompany(String company);

	Optional<Job> findById(Integer id);

}
