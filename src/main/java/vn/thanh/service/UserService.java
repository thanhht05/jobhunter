package vn.thanh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.thanh.domain.User;
import vn.thanh.respository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    public User handleGetUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;

    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public List<User> handleGetAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public User handleUpdateUser(User user) {
        Optional<User> userOptional = this.userRepository.findById(user.getId());

        if (userOptional.isPresent()) {

            User userUpdate = userOptional.get();

            userUpdate.setFullName(user.getFullName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());

            this.userRepository.save(userUpdate);
            return userUpdate;
        }
        return null;
    }
}
