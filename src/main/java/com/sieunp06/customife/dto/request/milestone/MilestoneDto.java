package com.sieunp06.customife.dto.request.milestone;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MilestoneDto {
    private String name;
    private String colorCode;

    @Builder
    private MilestoneDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }
}
