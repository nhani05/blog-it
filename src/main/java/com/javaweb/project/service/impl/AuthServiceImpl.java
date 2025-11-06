package com.javaweb.project.service.impl;

import com.javaweb.project.components.JwtUtil;
import com.javaweb.project.dto.request.LoginRequest;
import com.javaweb.project.dto.request.RegisterRequest;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.RoleRepository;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void addAccount(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("USERNAME ALREADY EXISTS");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDisplayName(request.getDisplayName());
        user.getRoles().add(roleRepository.findByName(("USER")));
        userRepository.save(user);
    }

    @Override
    public String loginAcccount(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        User user = userRepository.findByUsername(req.getUsername());
        return jwtUtil.generateToken(user);
    }
}
