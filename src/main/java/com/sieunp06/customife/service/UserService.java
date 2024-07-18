package com.sieunp06.customife.service;

import com.sieunp06.customife.config.jwt.CustomUserDetailsService;
import com.sieunp06.customife.config.jwt.JwtService;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.user.UserLoginDto;
import com.sieunp06.customife.dto.request.user.UserSignupDto;
import com.sieunp06.customife.dto.response.user.UserResponseDto;
import com.sieunp06.customife.dto.response.user.UserTokenDto;
import com.sieunp06.customife.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

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

    public UserTokenDto login(UserLoginDto userLoginDto) {
        String userEmail = userLoginDto.getUserEmail();
        String userPassword = userLoginDto.getUserPassword();

        authenticate(userEmail, userPassword);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        checkPassword(userPassword, userDetails.getPassword());

        String token = jwtService.generateToken(userDetails);
        return UserTokenDto.from(userEmail, token);
    }

    private void validateDuplicateUser(String userEmail) {
        userRepository.findByUserEmail(userEmail)
                .ifPresent(error -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    private void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new RuntimeException("인증되지 않은 아이디입니다.");
        } catch (BadCredentialsException e) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void checkPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new RuntimeException("비밀번호 불일치");
        }
    }
}
