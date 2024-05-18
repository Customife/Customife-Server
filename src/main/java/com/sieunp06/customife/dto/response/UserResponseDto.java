package com.sieunp06.customife.dto.response;

import com.sieunp06.customife.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String userEmail;
    private String userNickName;

    @Builder
    private UserResponseDto(String userEmail, String userNickName) {
        this.userEmail = userEmail;
        this.userNickName = userNickName;
    }

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userEmail(user.getUserEmail())
                .userNickName(user.getUserNickName())
                .build();
    }
}
