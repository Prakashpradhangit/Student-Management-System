package com.example.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


import com.example.student.Entity.Student;
import com.example.student.Entity.Teacher;
import com.example.student.Service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
public class control {

    @Autowired
    private StudentService studentService;


    @GetMapping("/student")
    public List<Student> getStudent() {
        return studentService.findStudent();
    }

    @GetMapping("/teacher")
    public List<Teacher> findTeacher(){
        return studentService.findTeacher();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentByID(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student stu){
        return studentService.createStudent(stu);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student stu) {
        return studentService.updateStudent(id, stu);
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<String> partialUpdateToDB(@PathVariable Long id, @RequestBody Student stu) {
        return studentService.partiallyUpdateStudent(id, stu);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
