package com.sieunp06.customife.dto.response.schedule;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Schedule;
import com.sieunp06.customife.domain.EventTag;
import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import com.sieunp06.customife.dto.response.milestone.MilestoneResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ScheduleResponseDto {
    private CategoryResponseDto category;
    private List<EventTag> eventTags;
    private MilestoneResponseDto milestone;
    private String content;
    private Date startDate;
    private Date endDate;
    private String memo;

    @Builder
    private ScheduleResponseDto(Category category, Milestone milestone,
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

    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .category(schedule.getCategory())
                .eventTags(schedule.getEventTags())
                .milestone(schedule.getMilestone())
                .content(schedule.getContent())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .memo(schedule.getMemo())
                .build();
    }
}
