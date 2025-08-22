package com.pgcloudit.springsecurity.controllers;

import com.pgcloudit.springsecurity.dtos.LoginResponseDto;
import com.pgcloudit.springsecurity.dtos.LoginUserDto;
import com.pgcloudit.springsecurity.dtos.RegisterUserDto;
import com.pgcloudit.springsecurity.entities.User;
import com.pgcloudit.springsecurity.services.AuthenticationService;
import com.pgcloudit.springsecurity.services.JwtTokenService;
import com.pgcloudit.springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.signup(registerUserDto));
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticationService.authenticate(loginUserDto));
    }

}
