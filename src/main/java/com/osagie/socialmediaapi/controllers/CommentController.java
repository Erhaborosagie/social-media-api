package com.osagie.socialmediaapi.controllers;

import com.osagie.socialmediaapi.dto.CommentDto;
import com.osagie.socialmediaapi.entities.Comment;
import com.osagie.socialmediaapi.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentsService service;

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> createPost(@RequestBody CommentDto dto, @PathVariable long postId){
        return ResponseEntity.ok(service.createComment(dto, postId));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable long id, @RequestBody CommentDto dto){
        return ResponseEntity.ok(service.UpdateComments(id, dto));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Comment> getOneComment(@PathVariable long id){
        return ResponseEntity.ok(service.getOneComment(id));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity deleteComments(@PathVariable long id){
        service.deleteComments(id);
        return ResponseEntity.ok().build();
    }

}
