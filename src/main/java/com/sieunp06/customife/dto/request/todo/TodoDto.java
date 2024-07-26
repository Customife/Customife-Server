package com.sieunp06.customife.dto.request.todo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class TodoDto {
    private String category;
    private String milestone;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Builder
    private TodoDto(String category, String milestone, String content, Date date) {
        this.category = category;
        this.milestone = milestone;
        this.content = content;
        this.date = date;
    }
}
