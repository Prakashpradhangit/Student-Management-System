package com.example.student.Service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.student.Entity.Teacher;
import com.example.student.Repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id){
        return teacherRepository.findById(id);
    }

    public ResponseEntity<Teacher> createTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }


}
