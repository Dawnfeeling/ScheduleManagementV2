package com.example.schedulemanagementv2.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private Long id;

    private final String username;

    private final String email;


    public SignUpResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
