package com.javaweb.project.service;

import com.javaweb.project.dto.response.UserDTO;

public interface UserService {
    public UserDTO findInformationUser(String username);
}
