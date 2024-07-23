package com.sieunp06.customife.dto.response.todo;

import com.sieunp06.customife.domain.Todo;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import com.sieunp06.customife.dto.response.milestone.MilestoneResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class TodoResponseDto {
    private CategoryResponseDto category;
    private MilestoneResponseDto milestone;
    private String content;
    private Date date;

    @Builder
    private TodoResponseDto(CategoryResponseDto category, MilestoneResponseDto milestone,
                            String content, Date date) {
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.date = date;
    }

    public static TodoResponseDto from(Todo todo) {
        return TodoResponseDto.builder()
                .category(CategoryResponseDto.from(todo.getCategory()))
                .milestone(MilestoneResponseDto.from(todo.getMilestone()))
                .content(todo.getContent())
                .date(todo.getDate())
                .build();
    }
}
