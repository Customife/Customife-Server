package com.sieunp06.customife.dto.response.milestone;

import com.sieunp06.customife.domain.Milestone;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MilestoneResponseDto {
    private String name;

    @Builder
    private MilestoneResponseDto(String name) {
        this.name = name;
    }

    public static MilestoneResponseDto from(Milestone milestone) {
        return MilestoneResponseDto.builder()
                .name(milestone.getName())
                .build();
    }
}
