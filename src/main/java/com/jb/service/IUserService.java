package com.jb.service;

import java.util.List;
import java.util.Optional;

import com.jb.model.User;

public interface IUserService {

    User registerUser(User user);

    String loginUser(String email, String password);

    String loginAdmin(String email, String password);

    Optional<User> findUserById(Integer id);

    List<User> findAllUsers();

    String editUser(Integer id, User user);

    void deleteUser(Integer id);

    Optional<User> findUserByEmail(String email);
}
