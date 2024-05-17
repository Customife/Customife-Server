package com.sieunp06.customife.dto.request.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSignupDto {
    private String userEmail;
    private String userPassword;
    private String userNickName;

    @Builder
    private UserSignupDto(String userEmail, String userPassword, String userNickName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickName = userNickName;
    }
}
