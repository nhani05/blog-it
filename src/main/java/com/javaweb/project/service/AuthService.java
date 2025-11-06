package com.javaweb.project.service;

import com.javaweb.project.dto.request.LoginRequest;
import com.javaweb.project.dto.request.RegisterRequest;

public interface AuthService {
    public void addAccount(RegisterRequest request);
    public String loginAcccount(LoginRequest request);
}
