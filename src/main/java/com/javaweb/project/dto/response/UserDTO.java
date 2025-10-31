package com.javaweb.project.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDTO {
    private String username;

    private String displayName;

}
