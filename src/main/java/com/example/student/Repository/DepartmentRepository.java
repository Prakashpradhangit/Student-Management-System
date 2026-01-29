package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
