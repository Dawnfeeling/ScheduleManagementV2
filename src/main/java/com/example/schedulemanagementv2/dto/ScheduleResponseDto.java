package com.example.schedulemanagementv2.dto;

import com.example.schedulemanagementv2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public ScheduleResponseDto(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updateAt = updatedAt;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    //일정 수정 시간 갱신
    public void updateTime(LocalDateTime updatedAt) {
        this.updateAt = updatedAt;
    }
}
