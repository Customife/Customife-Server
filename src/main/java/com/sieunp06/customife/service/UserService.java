package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.User.UserSignupDto;
import com.sieunp06.customife.dto.response.UserResponseDto;
import com.sieunp06.customife.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signup(UserSignupDto userSignupDto) {
        String userEmail = userSignupDto.getUserEmail();
        String encodedPassword = passwordEncoder.encode(userSignupDto.getUserPassword());
        String userNickName = userSignupDto.getUserNickName();

        User savedUser = userRepository.save(User.builder()
                .userEmail(userEmail)
                .userPassword(encodedPassword)
                .userNickName(userNickName)
                .build());

        return UserResponseDto.from(savedUser);
    }
}
