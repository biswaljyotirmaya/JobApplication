package com.jb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jb.model.Job;

public interface IJobRepository extends JpaRepository<Job, Integer> {

	@Query("FROM Job WHERE location = :location")
	List<Job> findByCity(String location);

	@Query("FROM Job WHERE salary = :salary")
	List<Job> findBySalary(Double salary);

	@Query("FROM Job WHERE title = :title")
	List<Job> findByTitle(String title);

	@Query("FROM Job WHERE company = :company")
	List<Job> findByCompany(String company);
}
