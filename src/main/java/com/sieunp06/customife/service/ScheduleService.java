package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Schedule;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.schedule.ScheduleAddDto;
import com.sieunp06.customife.dto.request.schedule.ScheduleUpdateDto;
import com.sieunp06.customife.dto.response.schedule.ScheduleResponseDto;
import com.sieunp06.customife.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final CategoryService categoryService;
    private final MilestoneService milestoneService;

    public List<ScheduleResponseDto> getSchedules(User user) {
        return scheduleRepository.findByUser(user).stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto addSchedule(ScheduleAddDto scheduleAddDto, User user) {
        Category category = categoryService.findCategory(scheduleAddDto.getCategory());
        Schedule schedule = scheduleRepository.save(Schedule.builder()
                        .user(user)
                        .category(category)
                        .content(scheduleAddDto.getContent())
                        .startDate(scheduleAddDto.getStartDate())
                        .endDate(scheduleAddDto.getEndDate())
                        .build());
        return ScheduleResponseDto.from(schedule);
    }

    public ScheduleResponseDto updateSchedule(User user, Long scheduleId, ScheduleAddDto scheduleAddDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, schedule.getUser());
        ScheduleUpdateDto scheduleUpdateDto = ScheduleUpdateDto.builder()
                .category(categoryService.findCategory(scheduleAddDto.getCategory()))
                .content(scheduleAddDto.getContent())
                .startDate(scheduleAddDto.getStartDate())
                .endDate(scheduleAddDto.getEndDate())
                .build();
        schedule.update(scheduleUpdateDto);
        return ScheduleResponseDto.from(schedule);
    }

    public void deleteSchedule(User user, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, schedule.getUser());
        scheduleRepository.delete(schedule);
    }

    private void validateUser(User user1, User user2) {
        if (!user1.equals(user2)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
