package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.student.Entity.Student;




@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);

}
