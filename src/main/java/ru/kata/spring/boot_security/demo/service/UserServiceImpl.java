package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User with id = " + id + " is not in the database");
        }

        userRepository.deleteById(id);
        return optionalUser.get();
    }

    @Override
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User with id = " + id + " is not in the database");
        }
        return optionalUser.get();
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByUsername(name);

        if (user == null) {
            throw new RuntimeException("User with name = " + name + " is not in the database");
        }
        return user;
    }
}
