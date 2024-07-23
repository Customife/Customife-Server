package com.sieunp06.customife.dto.response.milestone;

import com.sieunp06.customife.domain.Milestone;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MilestoneResponseDto {
    private String name;
    private String colorCode;

    @Builder
    private MilestoneResponseDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public static MilestoneResponseDto from(Milestone milestone) {
        return MilestoneResponseDto.builder()
                .name(milestone.getName())
                .colorCode(milestone.getColorCode())
                .build();
    }
}
