package com.example.onaffair.dto;


import com.example.onaffair.entity.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Integer userId;
    private String username;
    private String nickname;
    private String avatarUrl;
    private String email;
    private String phoneNumber;
}
