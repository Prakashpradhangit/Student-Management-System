package com.example.student.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Entity.Teacher;
import com.example.student.Service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeacherControl {

    private final TeacherService teacherService;

    
    @GetMapping("/teacher")
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }
}
