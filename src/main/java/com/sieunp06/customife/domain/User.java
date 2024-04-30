package com.sieunp06.customife.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.util.Objects;

@Getter
@Table(name = "user")
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", nullable = false)
    private String userEmail;       // 유저 이메일

    @Column(name = "user_password", nullable = false)
    private String userPassword;    // 유저 비밀번호

    @Column(name = "user_nickname", nullable = false)
    private String userNickName;    // 유저 닉네임

    @CreatedDate
    @Column(name = "user_created_at", nullable = false)
    private Date createdAt;         // 가입일

    protected User() {}

    private User(String userEmail, String userPassword, String userNickName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickName = userNickName;
    }

    public static User of(String userEmail, String userPassword, String userNickName) {
        return new User(userEmail, userPassword, userNickName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
