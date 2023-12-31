package com.osagie.socialmediaapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");
        final String token;
        final String userEmail;

        if (authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        token = authorization.substring(7);
        userEmail = jwtService.getUsernameFromToken(token);

        if (null != userEmail && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                        (userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
