package io.lpamintuan.springsectuts.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.lpamintuan.springsectuts.backend.models.User;
import io.lpamintuan.springsectuts.backend.services.interfaces.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @PostMapping(value="/signup")
    public User postMethodName(@RequestBody User user) {
        return userService.createUser(user);
    }
    

}
