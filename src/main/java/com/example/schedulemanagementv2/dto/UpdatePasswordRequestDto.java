package com.example.schedulemanagementv2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    @NotNull
    private final String oldPassword;

    @NotNull
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
