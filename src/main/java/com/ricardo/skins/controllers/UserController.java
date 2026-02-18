package com.ricardo.skins.controllers;

import com.ricardo.skins.models.Users;
import com.ricardo.skins.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Save a new User", tags = "User")
    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(users));
    }
}
