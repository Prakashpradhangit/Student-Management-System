package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.Entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
