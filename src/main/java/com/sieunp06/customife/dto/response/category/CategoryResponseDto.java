package com.sieunp06.customife.dto.response.category;

import com.sieunp06.customife.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private String name;
    private String colorCode;

    @Builder
    private CategoryResponseDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .name(category.getName())
                .colorCode(category.getColorCode())
                .build();
    }
}
