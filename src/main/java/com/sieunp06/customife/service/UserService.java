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

        validateDuplicateUser(userEmail);

        User savedUser = userRepository.save(User.builder()
                .userEmail(userEmail)
                .userPassword(encodedPassword)
                .userNickName(userNickName)
                .build());

        return UserResponseDto.from(savedUser);
    }

    private void validateDuplicateUser(String userEmail) {
        userRepository.findByUserEmail(userEmail)
                .ifPresent(error -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
