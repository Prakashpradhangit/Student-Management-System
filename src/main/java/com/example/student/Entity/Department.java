package com.example.student.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String departmentName;

    // One Department -> Many Students
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    // One Department -> Many Teachers
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new HashSet<>();

    // One Department -> Many Subjects
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();
}
