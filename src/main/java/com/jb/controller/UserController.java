package com.jb.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jb.enums.Role;
import com.jb.model.User;
import com.jb.service.IUserService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setRole(Role.USER);
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody User user) {
        user.setRole(Role.ADMIN);
        User newAdmin = userService.registerUser(user);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        String msg = userService.loginUser(email, password);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Integer id) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editProfile(@PathVariable Integer id, @RequestBody User user) {
        String msg = userService.editUser(id, user);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
