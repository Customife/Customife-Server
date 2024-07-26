package com.sieunp06.customife.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "color_code", nullable = false)
    private String colorCode;

    @OneToMany(mappedBy = "todoId")
    private List<Todo> todos = new ArrayList<>();

    public void update(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    protected Milestone() {}

    @Builder
    private Milestone(User user, String name, String colorCode) {
        this.user = user;
        this.name = name;
        this.colorCode = colorCode;
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
