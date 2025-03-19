package vn.thanh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.turkraft.springfilter.boot.Filter;

import vn.thanh.domain.User;
import vn.thanh.domain.dto.Meta;
import vn.thanh.domain.dto.ResultPaginationDTO;
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

    public ResultPaginationDTO handleGetAllUsers(
            Specification<User> spec, Pageable pageable) {
        Page<User> pageUser = this.userRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(pageUser.getNumber() + 1); // current page
        meta.setPageSize(pageUser.getSize()); // get maximum element

        meta.setPages(pageUser.getTotalPages());
        meta.setTotal(pageUser.getTotalElements()); // sum element in database

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(pageUser.getContent());

        return resultPaginationDTO;

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

    public User handleGetUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        return user;
    }
}
