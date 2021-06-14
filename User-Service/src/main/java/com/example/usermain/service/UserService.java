package com.example.usermain.service;

import com.example.usermain.exception.UserNotFoundException;
import com.example.usermain.repository.User;
import com.example.usermain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUser(Long id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("there is no such user"));
    }

    public User createUser(User usr) {
        return repo.save(usr);
    }

    public User updateUser(Long id, User new1) {
        User old1 = repo.findById(id).orElseThrow(() -> new UserNotFoundException("there is no such user"));
        old1.setName(new1.getName());
        old1.setCountry(new1.getCountry());
        return repo.save(old1);
    }

    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new UserNotFoundException("there is no such user");
        }
        repo.deleteById(id);
    }
}
