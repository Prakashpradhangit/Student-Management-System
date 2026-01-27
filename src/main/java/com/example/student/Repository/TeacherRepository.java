package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
