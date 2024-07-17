package com.sieunp06.customife.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Table(name = "event")
@Entity
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;              // 유저 아이디

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;      // 카테고리 아이디

    @OneToMany(mappedBy = "eventTagId")
    private List<EventTag> eventTags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;    // 마일스톤 아이디

    @Column(name = "content", nullable = false, length = 1000)
    private String content;         // 일정 내용

    @Column(name = "is_completed")
    private boolean isCompleted;    // 완료 여부

    @Column(name = "start_date")
    private Date startDate;         // 시작일

    @Column(name = "end_date")
    private Date endDate;           // 마감일

    @Column(name = "memo")
    private String memo;            // 메모

    protected Event() {}

    @Builder
    private Event(User user,
                  Category category,
                  Milestone milestone,
                  String content,
                  boolean isCompleted,
                  Date startDate, Date endDate,
                  String memo) {
        this.user = user;
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.isCompleted = isCompleted;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event event)) return false;
        return Objects.equals(eventId, event.getEventId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventId);
    }
}
