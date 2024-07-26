package com.sieunp06.customife.dto.request.category;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDto {
    private String name;
    private String colorCode;

    @Builder
    private CategoryDto(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }
}
