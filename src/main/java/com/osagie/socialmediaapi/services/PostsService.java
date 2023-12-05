package com.osagie.socialmediaapi.services;

import com.osagie.socialmediaapi.auth.AuthService;
import com.osagie.socialmediaapi.dto.PostDto;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final AuthService authService;
    private final PostRepository repository;

    public Post createPost(PostDto dto) {
        User user = authService.getCurrentUser();
        Post post = Post.builder()
                .content(dto.getContent())
                .likesCount(0)
                .date(new Date())
                .user(user)
                .build();

        return repository.save(post);
    }
}
