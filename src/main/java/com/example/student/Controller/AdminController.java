package com.example.student.Controller;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.DTO.OnBaordNewTeacher;
import com.example.student.DTO.StudentResponseDto;
import com.example.student.DTO.UserResponse;
import com.example.student.Entity.Teacher;
import com.example.student.Repository.TeacherRepository;
import com.example.student.Security.AuthService;
import com.example.student.Service.StudentService;
import com.example.student.Service.TeacherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final StudentService studentService;
    private final AuthService authService;
    private final TeacherService teacherService;



    private final TeacherControl teacherControl;

    @GetMapping("/student")
    public List<StudentResponseDto> getStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/teacher")
    public List<Teacher> getAllTeacher(){
        return teacherControl.getAllTeacher();
    }

    @GetMapping("/user")
    public List<UserResponse> getAllUser(){
        return authService.getAllUser();
    }

    @PostMapping("/newteacher")
    public ResponseEntity<Teacher> onBoardNewTeacher(@RequestBody OnBaordNewTeacher OnBaordNewTeacher ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.onBoardNewTeacher(OnBaordNewTeacher));
    }
    
 

}
