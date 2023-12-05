package com.osagie.socialmediaapi.controllers;

import com.osagie.socialmediaapi.dto.PostDto;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService service;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto dto){
        return ResponseEntity.ok(service.createPost(dto));
    }

}
