package com.library.libraryservice.feignclient;

import com.library.libraryservice.repository.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "User-Service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping(value = "/users")
    List<User> getUsers();

    @GetMapping(value = "/user/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping(value = "/user")
    User createUser(@RequestBody User usr);

    @PutMapping(value = "/updateUser/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User usr);

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id);


}
