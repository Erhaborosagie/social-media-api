package com.osagie.socialmediaapi.services;

import com.osagie.socialmediaapi.auth.AuthService;
import com.osagie.socialmediaapi.dto.PostDto;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post UpdatePosts(long id, PostDto dto) {
        Optional<Post> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        Post post = byId.get();
        post.setContent(dto.getContent());

        return repository.save(post);
    }

    public void deletePosts(long id) {
        repository.deleteById(id);
    }

    public Post getOnePost(long id) {
        Optional<Post> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        return byId.get();
    }
}
