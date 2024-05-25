package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.user.UserSignupDto;
import com.sieunp06.customife.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("[UserService Test]")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void signup() throws Exception {
        // Given
        UserSignupDto userSignupDto = createUserDto();
        User user = createUser();

        given(userRepository.save(any(User.class)))
                .willReturn(user);
        given(userRepository.findByUserEmail(userSignupDto.getUserEmail()))
                .willReturn(Optional.empty())
                .willReturn(Optional.of(user));

        // When
        userService.signup(userSignupDto);

        // Then
        assertThat(userRepository.findByUserEmail(userSignupDto.getUserEmail()).isEmpty())
                .isFalse();
    }

    @Test
    void signup_duplicate_user() throws Exception {
        // Given
        UserSignupDto userSignupDto = createUserDto();
        User user = createUser();

        given(userRepository.save(any(User.class)))
                .willReturn(user);
        given(userRepository.findByUserEmail(userSignupDto.getUserEmail()))
                .willReturn(Optional.empty())
                .willReturn(Optional.of(user));

        userService.signup(userSignupDto);

        // When & Then
        assertThatThrownBy(() -> userService.signup(userSignupDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 회원입니다.");
    }

    private static UserSignupDto createUserDto() {
        return UserSignupDto.builder()
                .userEmail("test@email.com")
                .userPassword("test1234")
                .userNickName("testNickName")
                .build();
    }

    private static User createUser() {
        return User.builder()
                .userEmail("test@email.com")
                .userPassword("encodedPassword")
                .userNickName("testNickName")
                .build();
    }
}