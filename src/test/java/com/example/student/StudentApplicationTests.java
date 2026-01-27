package com.example.student;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.student.Entity.Student;
import com.example.student.Repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class StudentApplicationTests {

	private final StudentRepository studentRepository;

	@Test
	public void testStudent(){
		Student student = studentRepository.findByName("prakash pradhan");
		System.out.println(student);
	}

}
