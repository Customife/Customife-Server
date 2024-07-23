package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.milestone.MilestoneDto;
import com.sieunp06.customife.dto.response.milestone.MilestoneResponseDto;
import com.sieunp06.customife.repository.MilestoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public List<MilestoneResponseDto> getMilestone(User user) {
        return milestoneRepository.findByUser(user).stream()
                .map(MilestoneResponseDto::from)
                .collect(Collectors.toList());
    }

    public Milestone findMilestone(String name) {
        return milestoneRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
    }

    public MilestoneResponseDto addMilestone(User user, MilestoneDto milestoneDto) {
        validateName(user, milestoneDto.getName());
        Milestone milestone = milestoneRepository.save(Milestone.builder()
                        .user(user)
                        .name(milestoneDto.getName())
                        .colorCode(milestoneDto.getColorCode())
                        .build());
        return MilestoneResponseDto.from(milestone);
    }

    public MilestoneResponseDto updateMilestone(User user, Long milestoneId, MilestoneDto milestoneDto) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, milestone.getUser());
        milestone.update(milestoneDto.getName(), milestoneDto.getColorCode());
        return MilestoneResponseDto.from(milestone);
    }

    public void deleteMilestone(User user, Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, milestone.getUser());
        milestoneRepository.delete(milestone);
    }

    private void validateName(User user, String name) {
        System.out.println(milestoneRepository.findByUserAndName(user, name));
        milestoneRepository.findByUserAndName(user, name)
                .ifPresent(error -> {
                    throw new IllegalArgumentException("이미 존재하는 마일스톤입니다.");
                });
    }

    private void validateUser(User user1, User user2) {
        if (!user1.equals(user2)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
