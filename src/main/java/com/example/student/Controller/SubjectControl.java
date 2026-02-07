package com.example.student.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.student.Entity.Subject;
import com.example.student.Service.SubjectService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SubjectControl {

    private final SubjectService subjectService;

    @GetMapping("/subject")
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @PostMapping("/subject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        return subjectService.createSubject(subject);
    }
    
}
