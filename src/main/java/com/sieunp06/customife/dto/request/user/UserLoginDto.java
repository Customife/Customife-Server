package com.sieunp06.customife.dto.request.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginDto {
    private String userEmail;
    private String userPassword;

    @Builder
    private UserLoginDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
