package com.jb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
