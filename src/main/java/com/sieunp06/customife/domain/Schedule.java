package com.sieunp06.customife.domain;

import com.sieunp06.customife.dto.request.schedule.ScheduleUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
@Table(name = "schedule")
@Entity
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;              // 유저 아이디

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;      // 카테고리 아이디

    @Column(name = "content", nullable = false, length = 1000)
    private String content;         // 일정 내용

    @Column(name = "start_date")
    private Date startDate;         // 시작일

    @Column(name = "end_date")
    private Date endDate;           // 마감일

    public void update(ScheduleUpdateDto scheduleUpdateDto) {
        this.category = scheduleUpdateDto.getCategory();
    }

    protected Schedule() {}

    @Builder
    private Schedule(User user,
                     Category category,
                     String content,
                     Date startDate, Date endDate) {
        this.user = user;
        this.category = category;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        return Objects.equals(scheduleId, schedule.getScheduleId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(scheduleId);
    }
}
