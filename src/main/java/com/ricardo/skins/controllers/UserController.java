package com.ricardo.skins.controllers;

import com.ricardo.skins.models.Users;
import com.ricardo.skins.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Find all Users", tags = "User")
    @GetMapping
    public ResponseEntity<List< Users>> findAll(){
        return  ResponseEntity.ok(userService.findAll());

    }
    @Operation(summary = "Find User by ID", tags = "User")
    @GetMapping("/{id}")
    public  ResponseEntity<Users> findByID(@PathVariable Long id){
        Users user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Save a new User", tags = "User")
    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(users));
    }

    @Operation(summary = "Update User", tags = "User")
    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@RequestBody Users user,@PathVariable Long id){
        return ResponseEntity.ok(userService.update(id,user));
    }

    @Operation(summary = "Delete User", tags = "User")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
       userService.delete(id);
    }
}
