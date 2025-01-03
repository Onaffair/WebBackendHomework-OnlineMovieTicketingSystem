package com.example.onaffair.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String nickname;
    private String avatar;

}
