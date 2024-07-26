package com.sieunp06.customife.dto.request.schedule;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class ScheduleAddDto {
    private String category;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Builder
    private ScheduleAddDto(String category,
                           String content,
                           Date startDate, Date endDate) {
        this.category = category;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
