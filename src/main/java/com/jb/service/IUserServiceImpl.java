package com.jb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jb.enums.Role;
import com.jb.exception.ResourceNotFoundException;
import com.jb.model.User;
import com.jb.repository.IUserRepository;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;

	@Override
	public User registerUser(User user) {
		if (userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("User is already registered!");
		}
		return userRepo.save(user);
	}

	@Override
	public String loginUser(String email, String password) {
		return userRepo.findByEmail(email).filter(user -> user.getPassword().equals(password))
				.filter(user -> Role.USER.equals(user.getRole()))
				.map(user -> user.getName() + " logged in successfully!")
				.orElseThrow(() -> new RuntimeException("Invalid login credentials!"));
	}

	@Override
	public String loginAdmin(String email, String password) {
		return userRepo.findByEmail(email).filter(user -> user.getPassword().equals(password))
				.filter(user -> Role.ADMIN.equals(user.getRole()))
				.map(user -> user.getName() + " logged in successfully!")
				.orElseThrow(() -> new RuntimeException("Invalid login credentials!"));
	}

	@Override
	public Optional<User> findUserById(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String editUser(Integer id, User updatedUser) {
		User existingUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

		// Update only non-null fields
		if (updatedUser.getName() != null)
			existingUser.setName(updatedUser.getName());
		if (updatedUser.getEmail() != null)
			existingUser.setEmail(updatedUser.getEmail());
		if (updatedUser.getPassword() != null)
			existingUser.setPassword(updatedUser.getPassword());
		if (updatedUser.getRole() != null)
			existingUser.setRole(updatedUser.getRole());

		userRepo.save(existingUser);
		return "User details updated for ID: " + id;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		userRepo.delete(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
