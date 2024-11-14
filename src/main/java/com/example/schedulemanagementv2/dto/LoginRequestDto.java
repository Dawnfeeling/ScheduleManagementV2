package com.example.schedulemanagementv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NonNull
    private final String email;

    @NonNull
    private final String password;
}
