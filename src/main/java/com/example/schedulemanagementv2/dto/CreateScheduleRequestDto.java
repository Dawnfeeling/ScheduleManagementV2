package com.example.schedulemanagementv2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotNull
    private final String title;

    @NotNull
    private final String contents;

    @NotNull
    private final String username;

    public CreateScheduleRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
