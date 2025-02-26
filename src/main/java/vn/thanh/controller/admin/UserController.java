package vn.thanh.controller.admin;

import org.springframework.web.bind.annotation.RestController;

import vn.thanh.domain.User;
import vn.thanh.service.UserService;
import vn.thanh.service.error.IdInvalidException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String hello() {
        return "hello.java";
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User userThanh) {
        String hashPassword = passwordEncoder.encode(userThanh.getPassword());
        userThanh.setPassword(hashPassword);
        User newUser = this.userService.handleCreateUser(userThanh);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if (id > 100) {
            throw new IdInvalidException("Id should be less than 100");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok("delete ok");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") long id) {
        User user = userService.handleGetUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.handleGetAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users")
    public ResponseEntity<User> putMethodName(@RequestBody User user) {
        User userUpdate = this.userService.handleCreateUser(user);
        return ResponseEntity.ok(userUpdate);
    }

}
