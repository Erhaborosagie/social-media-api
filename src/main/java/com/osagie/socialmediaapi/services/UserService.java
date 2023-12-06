package com.osagie.socialmediaapi.services;

import com.osagie.socialmediaapi.dto.UserDto;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User UpdateUsers(long id, UserDto dto) {
        Optional<User> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        User user = byId.get();
        if (null != dto.getEmail()) user.setEmail(dto.getEmail());
        if (null != dto.getName()) user.setName(dto.getName());

        return repository.save(user);
    }

    public void deleteUsers(long id) {
        repository.deleteById(id);
    }

    public User getOneUser(long id) {
        Optional<User> byId = repository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        return byId.get();
    }
}
