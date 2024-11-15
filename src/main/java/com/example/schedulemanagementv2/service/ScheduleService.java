package com.example.schedulemanagementv2.service;

import com.example.schedulemanagementv2.dto.ScheduleResponseDto;
import com.example.schedulemanagementv2.entity.Schedule;
import com.example.schedulemanagementv2.entity.User;
import com.example.schedulemanagementv2.repository.ScheduleRepository;
import com.example.schedulemanagementv2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //일정 생성
    public ScheduleResponseDto save(String title, String contents, String username){

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    //일정 전체 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //일정 선택 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.update(title, contents);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());
    }

    //일정 삭제
    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
