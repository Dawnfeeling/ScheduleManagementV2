package com.example.schedulemanagementv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull
    @Email
    private final String email;

    @NotNull
    private final String password;
}
