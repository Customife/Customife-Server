package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.schedule.ScheduleAddDto;
import com.sieunp06.customife.dto.response.schedule.ScheduleResponseDto;
import com.sieunp06.customife.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/get")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedules(user));
    }

    @PostMapping("/add")
    public ResponseEntity<ScheduleResponseDto> addSchedule(
            @RequestBody ScheduleAddDto scheduleAddDto,
            @AuthenticationPrincipal User user) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.addSchedule(scheduleAddDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto);
    }

    @PatchMapping("/{eventId}/update")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleId,
            @RequestBody ScheduleAddDto scheduleAddDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(user, scheduleId, scheduleAddDto));
    }

    @DeleteMapping("/{eventId}/delete")
    public ResponseEntity<Long> deleteSchedule(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(user, scheduleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
