package com.osagie.socialmediaapi.controllers;

import com.osagie.socialmediaapi.dto.PostDto;
import com.osagie.socialmediaapi.dto.UserDto;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUsers(@PathVariable long id, @RequestBody UserDto dto){
        return ResponseEntity.ok(service.UpdateUsers(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable long id){
        return ResponseEntity.ok(service.getOneUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsers(@PathVariable long id){
        service.deleteUsers(id);
        return ResponseEntity.ok().build();
    }
}
