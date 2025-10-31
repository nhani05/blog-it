package com.javaweb.project.controller;

import com.javaweb.project.dto.response.UserDTO;
import com.javaweb.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<UserDTO> getProfileUser(@RequestParam("username") String username) {
        UserDTO userDTO = userService.findInformationUser(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
