package com.javaweb.project.service;

import com.javaweb.project.dto.response.UserDTO;
import com.javaweb.project.entity.User;

public interface UserService {
    public UserDTO findInformationUser(String username);
    public User findByUsername(String username);
}
