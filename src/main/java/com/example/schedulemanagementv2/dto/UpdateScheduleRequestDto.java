package com.example.schedulemanagementv2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateScheduleRequestDto {

    @NotNull
    private final String title;

    @NotNull
    @Length(min = 1, max = 255)
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
