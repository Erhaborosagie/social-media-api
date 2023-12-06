package com.osagie.socialmediaapi.controllers;

import com.osagie.socialmediaapi.dto.PostDto;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService service;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto dto){
        return ResponseEntity.ok(service.createPost(dto));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(service.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePosts(@PathVariable long id, @RequestBody PostDto dto){
        return ResponseEntity.ok(service.UpdatePosts(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getOnePost(@PathVariable long id){
        return ResponseEntity.ok(service.getOnePost(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePosts(@PathVariable long id){
        service.deletePosts(id);
        return ResponseEntity.ok().build();
    }

}
