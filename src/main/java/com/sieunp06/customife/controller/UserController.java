package com.sieunp06.customife.controller;

import com.sieunp06.customife.dto.request.User.UserSignupDto;
import com.sieunp06.customife.dto.response.UserResponseDto;
import com.sieunp06.customife.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserSignupDto userSignupDto) {
        UserResponseDto userResponseDto = userService.signup(userSignupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }
}
