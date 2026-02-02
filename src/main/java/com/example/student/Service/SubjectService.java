package com.example.student.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.student.Entity.Subject;
import com.example.student.Repository.SubjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    public ResponseEntity<Subject> createSubject(Subject subject){
        subjectRepository.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subject);
    }

    

}
