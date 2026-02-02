package com.example.student.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.DTO.LoginRequestDto;
import com.example.student.DTO.LoginResponseDto;
import com.example.student.DTO.SignupResponseDto;
import com.example.student.Entity.Department;
import com.example.student.Security.AuthService;
import com.example.student.Service.DepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> getDepartment(){
        return departmentService.getAllDepartments();
    }
    

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto ){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody LoginRequestDto signUpRequestDto ){
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }

}
