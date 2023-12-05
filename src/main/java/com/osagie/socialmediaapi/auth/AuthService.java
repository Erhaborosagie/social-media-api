package com.osagie.socialmediaapi.auth;

import com.osagie.socialmediaapi.config.JwtService;
import com.osagie.socialmediaapi.entities.User;
import com.osagie.socialmediaapi.enums.Role;
import com.osagie.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.ADMIN)
                .build();

        var token = jwtService.generateToken(user);

        userRepository.save(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return AuthResponse.builder().token(token).build();
    }

    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal= (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(principal.getUsername()).orElseThrow();
    }
}
