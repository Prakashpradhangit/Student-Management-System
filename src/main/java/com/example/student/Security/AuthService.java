package com.example.student.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final UserRespository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public SignupResponseDto signUp(LoginRequestDto signUpRequestDto) {

        if (userRepository.findByUsername(signUpRequestDto.getUsername()).isPresent())
            throw new IllegalArgumentException("User already exists");

        User user = userRepository.save(User.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build());

        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    // public String newLogin(LoginRequestDto dto) {

    //     try {

    //         authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(
    //                         dto.getUsername(),
    //                         dto.getPassword()));

    //         return "SUCCESS";

    //     } catch (BadCredentialsException e) {
    //         return "Bad credentials";
    //     } catch (UsernameNotFoundException e) {
    //         return "User not found";
    //     }
    // }

}
