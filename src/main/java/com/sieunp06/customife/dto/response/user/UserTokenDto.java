package com.sieunp06.customife.dto.response.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserTokenDto {
    private String userEmail;
    private String token;

    @Builder
    private UserTokenDto(String userEmail, String token) {
        this.userEmail = userEmail;
        this.token = token;
    }

    public static UserTokenDto from(String userEmail, String token) {
        return UserTokenDto.builder()
                .userEmail(userEmail)
                .token(token)
                .build();
    }
}
