package com.library.libraryservice.controller;

import com.library.libraryservice.repository.User;
import com.library.libraryservice.feignclient.UserClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/library")
public class UserController {

    @Autowired
    private UserClient uclient;


    @GetMapping(value="/users")
    @HystrixCommand(fallbackMethod= "defaultResponse")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(uclient.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value="/user/{id}")
    @HystrixCommand(fallbackMethod= "defaultResponse")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<User>(uclient.getUser(id), HttpStatus.OK);
    }

    @PostMapping(value="/user")
    @HystrixCommand(fallbackMethod= "defaultResponse")
    public ResponseEntity<User> createUser(@RequestBody User usr){
        return new ResponseEntity<User>(uclient.createUser(usr), HttpStatus.CREATED);
    }

    @PutMapping(value="/updateUser/{id}")
    @HystrixCommand(fallbackMethod= "defaultResponse")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User usr){
        return new ResponseEntity<User>(uclient.updateUser(id,usr), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/deleteUser/{id}")
    @HystrixCommand(fallbackMethod= "defaultResponse")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        uclient.deleteUser(id);
        return ResponseEntity.notFound().build();
    }

    //Fallback Method
    public ResponseEntity<String> defaultResponse(String err) {
        System.out.println("You are seeing this fallback response because the underlying microservice is down.");
        err = "Fallback error as the microservice is down.";
        return new ResponseEntity<String>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
