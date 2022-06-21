package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User deleteUserById(int id);
    User updateUser(User user);
    User getUserById(int id);
    User getUserByName(String name);
}
