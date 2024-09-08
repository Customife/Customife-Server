package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.milestone.MilestoneDto;
import com.sieunp06.customife.dto.response.milestone.MilestoneResponseDto;
import com.sieunp06.customife.service.MilestoneService;
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
@RequestMapping("/milestone")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/get")
    public ResponseEntity<List<MilestoneResponseDto>> getMilestone(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(milestoneService.getMilestone(user));
    }

    @PostMapping("/add")
    public ResponseEntity<MilestoneResponseDto> addMilestone(
            @AuthenticationPrincipal User user,
            @RequestBody MilestoneDto milestoneDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(milestoneService.addMilestone(user, milestoneDto));
    }

    @PatchMapping("{milestoneName}/update")
    public ResponseEntity<MilestoneResponseDto> updateMilestone(
            @AuthenticationPrincipal User user,
            @PathVariable String milestoneName,
            @RequestBody MilestoneDto milestoneDto) {
        MilestoneResponseDto milestoneResponseDto = milestoneService.updateMilestone(user, milestoneName, milestoneDto);
        return ResponseEntity.status(HttpStatus.OK).body(milestoneResponseDto);
    }

    @DeleteMapping("{milestoneName}/delete")
    public ResponseEntity<Long> deleteMilestone(
            @AuthenticationPrincipal User user,
            @PathVariable String milestoneName
    ) {
        milestoneService.deleteMilestone(user, milestoneName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
