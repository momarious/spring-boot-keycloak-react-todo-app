package com.momarious.userapi.controller;

import com.momarious.userapi.dto.UserDto;
import com.momarious.userapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws Exception {
        try {
            return ResponseEntity.ok(userService.createUser(userDto));
        } catch (Exception e) {
            throw new Exception("Error creating user", e);
        }
    }

}
