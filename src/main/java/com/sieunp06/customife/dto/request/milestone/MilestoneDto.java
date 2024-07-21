package com.sieunp06.customife.dto.request.milestone;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MilestoneDto {
    private String name;

    @Builder
    private MilestoneDto(String name) {
        this.name = name;
    }
}
