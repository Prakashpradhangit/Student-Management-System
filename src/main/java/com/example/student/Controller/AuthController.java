package com.example.student.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.DTO.LoginRequestDto;
import com.example.student.DTO.LoginResponseDto;
import com.example.student.DTO.SignupResponseDto;
import com.example.student.DTO.UserResponse;
import com.example.student.Entity.User;
import com.example.student.Security.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private  final AuthService authService;
    

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody LoginRequestDto loginRequestDto){
        SignupResponseDto dto = authService.signup(loginRequestDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user")
    public List<UserResponse> getAllUser(){
        return authService.getAllUser();
    }
    
   
}
