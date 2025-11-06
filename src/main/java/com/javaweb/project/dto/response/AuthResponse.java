package com.javaweb.project.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // hoặc dùng @Getter, @Setter của Lombok
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
}
