package com.sieunp06.customife.dto.response.event;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Event;
import com.sieunp06.customife.domain.EventTag;
import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import com.sieunp06.customife.dto.response.milestone.MilestoneResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class EventResponseDto {
    private CategoryResponseDto category;
    private List<EventTag> eventTags;
    private MilestoneResponseDto milestone;
    private String content;
    private Date startDate;
    private Date endDate;
    private String memo;

    @Builder
    private EventResponseDto(Category category, Milestone milestone,
                             List<EventTag> eventTags,
                             String content,
                             Date startDate, Date endDate,
                             String memo) {
        this.category = CategoryResponseDto.from(category);
        this.eventTags = eventTags;
        this.milestone = MilestoneResponseDto.from(milestone);
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
    }

    public static EventResponseDto from(Event event) {
        return EventResponseDto.builder()
                .category(event.getCategory())
                .eventTags(event.getEventTags())
                .milestone(event.getMilestone())
                .content(event.getContent())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .memo(event.getMemo())
                .build();
    }
}
