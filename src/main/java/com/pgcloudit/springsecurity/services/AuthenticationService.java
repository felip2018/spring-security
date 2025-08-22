package com.pgcloudit.springsecurity.services;

import com.pgcloudit.springsecurity.dtos.LoginResponseDto;
import com.pgcloudit.springsecurity.dtos.LoginUserDto;
import com.pgcloudit.springsecurity.dtos.RegisterUserDto;
import com.pgcloudit.springsecurity.entities.User;
import com.pgcloudit.springsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public LoginResponseDto authenticate(LoginUserDto loginUserDto) {
        var token = new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        String jwtToken = jwtTokenService.generateToken(authentication);
        Long expiresAt = jwtTokenService.extractExpirationTime(jwtToken);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(expiresAt);
        return loginResponseDto;
    }
}
