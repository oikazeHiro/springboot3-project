package com.oik.api.utils.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String username;
    private String deptId;
    private String email;
    private String mobile;
    private String description;
    private String token;
    private String ssex;
    private String avatar;
    private String ip;
    private LocalDateTime lastLoginTime;
    private String loginAddress;
    private String other;

    public UserDTO(String username) {
        this.username = username;
    }
}
