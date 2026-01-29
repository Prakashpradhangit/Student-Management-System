package com.example.student.Entity;

import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false, unique = true)
    private String subjectCode;

    // Inverse side of Student <-> Subject
    @ManyToMany(mappedBy = "subjects")
    private Set<Student> students = new HashSet<>();

    // Many Subjects -> One Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // One Subject -> Many Teachers
    @OneToMany(mappedBy = "subject")
    private Set<Teacher> teachers = new HashSet<>();



}
