package com.example.student.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.student.Entity.Department;
import com.example.student.Repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    } 

    public ResponseEntity<Department> createDeapartment(Department department){
        departmentRepository.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

}
