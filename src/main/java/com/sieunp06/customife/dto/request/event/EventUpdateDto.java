package com.sieunp06.customife.dto.request.event;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Milestone;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class EventUpdateDto {
    private Category category;
    private Milestone milestone;
    private String content;
    private Date startDate;
    private Date endDate;
    private String memo;

    @Builder
    private EventUpdateDto(Category category, Milestone milestone,
                           String content, String memo,
                           Date startDate, Date endDate) {
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
    }
}
