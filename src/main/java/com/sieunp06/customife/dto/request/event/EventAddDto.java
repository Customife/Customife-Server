package com.sieunp06.customife.dto.request.event;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class EventAddDto {
    private String category;
    private String milestone;
    private String content;
    private String memo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Builder
    private EventAddDto(String category, String milestone,
                        String content,
                        Date startDate, Date endDate,
                        String memo) {
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
    }
}
