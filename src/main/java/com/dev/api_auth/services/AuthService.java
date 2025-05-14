package com.dev.api_auth.services;

import com.dev.api_auth.jwt.JwtUtil;
import com.dev.api_auth.models.dtos.RegisterUserDto;
import com.dev.api_auth.models.entitites.Role;
import com.dev.api_auth.models.entitites.User;
import com.dev.api_auth.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String authenticateUser(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);

        String[] credentials = decodedCredentials.split(":", 2);
        if (credentials.length != 2) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String email = credentials[0];
        String password = credentials[1];

        User user = userService.getUserByEmail(email);
        if (passwordEncoder.matches(password, Objects.nonNull(user) ? user.getPassword() : "")) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtUtil.generateToken(authentication);
        } else {
            return "Invalid credentials";
        }
    }

    public void registerUser(RegisterUserDto registerUserDto) {
        if (userService.existsByEmail(registerUserDto.getEmail())) {
            throw new IllegalArgumentException("Email address already in use");
        }

        List<Role> roles = roleRepository.findByNameIn(registerUserDto.getRoles());
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Invalid roles");
        }

        User user = new User(registerUserDto.getName(), registerUserDto.getLastName(), registerUserDto.getEmail(), passwordEncoder.encode(registerUserDto.getPassword()), new HashSet<>(roles));

        userService.save(user);
    }
}
