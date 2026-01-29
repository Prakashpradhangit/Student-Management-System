package com.example.student.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Entity.Department;
import com.example.student.Service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DeapartmentControl {
    
    private final DepartmentService departmentService;

    @GetMapping("/department")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepaerment(@RequestBody Department department){
        return departmentService.createDeapartment(department);
    }
    
}
