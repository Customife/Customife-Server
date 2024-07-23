package com.sieunp06.customife.dto.request.schedule;

import com.sieunp06.customife.domain.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleUpdateDto {
    private Category category;
    private String content;
    private Date startDate;
    private Date endDate;
    private String memo;

    @Builder
    private ScheduleUpdateDto(Category category,
                              String content,
                              Date startDate, Date endDate) {
        this.category = category;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
