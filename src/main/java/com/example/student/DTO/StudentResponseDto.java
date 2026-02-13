package com.example.student.DTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.student.Entity.Subject;

import lombok.Data;

@Data
public class StudentResponseDto {
    private LocalDateTime createdAt;
    private Long id;
    private String name;
    private String email;
    private char gender;
    private String department;
    private Set<Subject> subjects = new HashSet<>();
}
