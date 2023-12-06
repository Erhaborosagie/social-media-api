package com.osagie.socialmediaapi.services;

import com.osagie.socialmediaapi.auth.AuthService;
import com.osagie.socialmediaapi.dto.CommentDto;
import com.osagie.socialmediaapi.entities.Comment;
import com.osagie.socialmediaapi.entities.Post;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.repositories.CommentRepository;
import com.osagie.socialmediaapi.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final AuthService authService;
    private final CommentRepository repository;
    private final PostRepository postRepository;

    public Comment createComment(CommentDto dto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        User user = authService.getCurrentUser();
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .date(new Date())
                .user(user)
                .post(post)
                .build();

        return repository.save(comment);
    }

    public Comment UpdateComments(long id, CommentDto dto) {
        Optional<Comment> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        User user = authService.getCurrentUser();

        User commentUser = byId.get().getUser();

        if (user.getId() != commentUser.getId()){
            throw new ResponseStatusException(FORBIDDEN, "U can't update another's comment");
        }

        Comment comment = byId.get();
        comment.setContent(dto.getContent());

        return repository.save(comment);
    }

    public void deleteComments(long id) {
        Optional<Comment> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        User user = authService.getCurrentUser();

        User commentUser = byId.get().getUser();

        if (user.getId() != commentUser.getId()){
            throw new ResponseStatusException(FORBIDDEN, "U can't update another's comment");
        }
        repository.deleteById(id);
    }

    public Comment getOneComment(long id) {
        Optional<Comment> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        return byId.get();
    }
}
