package com.javaweb.project.dto.response;


import lombok.*;

@Data  // hoặc dùng @Getter, @Setter của Lombok
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
public class AuthResponse {
    private String token;
}
