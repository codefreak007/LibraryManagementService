package com.example.usermain.controller;

import com.example.usermain.config.SwaggerConfig;
import com.example.usermain.repository.User;
import com.example.usermain.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<User>(service.getUser(id), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User usr) {
        return new ResponseEntity<User>(service.createUser(usr), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User usr) {
        return new ResponseEntity<User>(service.updateUser(id, usr), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.notFound().build();
    }
}

