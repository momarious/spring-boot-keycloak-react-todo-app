package com.momarious.userapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping(value = "/user-data")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("You are logged in with the following username and password");
    }

    @GetMapping(value = "/admin-data")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("You are an admin");
    }

    @GetMapping(value = "/protected-data")
    public ResponseEntity<String> getPotectedData() {
        return ResponseEntity.ok("Hello, this api is protected.");
    }

    @GetMapping(value = "/unprotected-data")
    public ResponseEntity<String> getUnprotectedData() {
        return ResponseEntity.ok("Hello, this api is unprotected.");
    }


}
