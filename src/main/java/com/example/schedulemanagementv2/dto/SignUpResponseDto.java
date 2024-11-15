package com.example.schedulemanagementv2.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpResponseDto {
    private Long id;

    private final String username;

    private final String email;

    private final LocalDateTime createdAt;

    public SignUpResponseDto(Long id, String username, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
}
