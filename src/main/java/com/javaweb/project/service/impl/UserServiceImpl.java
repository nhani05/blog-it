package com.javaweb.project.service.impl;

import com.javaweb.project.dto.response.UserDTO;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO findInformationUser(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserDTO.class);
    }
}
