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
import java.util.List;
import java.util.Objects;

@Getter
@Table(name = "milestone")
@Entity
public class Milestone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long milestoneId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "eventId")
    private List<Event> events = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    public void update(String name) {
        this.name = name;
    }

    protected Milestone() {}

    @Builder
    private Milestone(User user, String name) {
        this.user = user;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milestone milestone)) return false;
        return Objects.equals(milestoneId, milestone.milestoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(milestoneId);
    }
}
