package com.sieunp06.customife.dto.response.schedule;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Schedule;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private CategoryResponseDto category;
    private String content;
    private Date startDate;
    private Date endDate;

    @Builder
    private ScheduleResponseDto(Category category,
                                String content,
                                Date startDate, Date endDate) {
        this.category = CategoryResponseDto.from(category);
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .category(schedule.getCategory())
                .content(schedule.getContent())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }
}
