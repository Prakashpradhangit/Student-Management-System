package com.example.student.Security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.student.DTO.LoginRequestDto;
import com.example.student.DTO.LoginResponseDto;
import com.example.student.DTO.SignupResponseDto;
import com.example.student.Entity.User;
import com.example.student.Repository.UserRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

    Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
            );

    User user = (User) authentication.getPrincipal();

    String token = authUtil.generateJwtToken(user);

    return new LoginResponseDto(token, user.getId());
}


    public SignupResponseDto signUp(LoginRequestDto signUpRequestDto){
        User user = userRespository.findByUsername(signUpRequestDto.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User already exist");  

        user = userRespository.save(User.builder()
                                .username(signUpRequestDto.getUsername())
                                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                                .build()
        );

        return new SignupResponseDto(user.getId(), user.getUsername());


    }
}
