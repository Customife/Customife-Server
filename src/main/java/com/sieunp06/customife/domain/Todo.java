package com.sieunp06.customife.domain;

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
@Table(name = "todo")
@Entity
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    @Column(name = "date")
    private Date date;

    public void changeCompleted() {
        isCompleted = !isCompleted;
    }

    protected Todo() {}

    @Builder
    private Todo(User user, Category category, Milestone milestone, String content, Date date) {
        this.user = user;
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.date = date;
        this.isCompleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo todo)) return false;
        return Objects.equals(todoId, todo.todoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(todoId);
    }
}
