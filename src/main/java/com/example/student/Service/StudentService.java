package com.example.student.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.student.Entity.Student;
import com.example.student.Entity.Teacher;
import com.example.student.Repository.StudentRepository;
import com.example.student.Repository.TeacherRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public List<Student> findStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public ResponseEntity<Student> createStudent(Student stu) {
        studentRepository.save(stu);
        return ResponseEntity.status(HttpStatus.CREATED).body(stu);
    }

    public List<Teacher> findTeacher(){
        return teacherRepository.findAll();
    }

    public ResponseEntity<String> updateStudent(Long id, Student stu) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if ((stu.getName() != null) & (stu.getEmail() != null) & (stu.getGender() != 0) & (stu.getPhone() != null)) {
            if (existingStudent != null) {
                existingStudent.setName(stu.getName());
                existingStudent.setEmail(stu.getEmail());
                existingStudent.setPhone(stu.getPhone());
                existingStudent.setGender(stu.getGender());
                studentRepository.save(existingStudent);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Student updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student not found with id " + id);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All field required ");
        }
    }

    public ResponseEntity<String> partiallyUpdateStudent(Long id, Student stu) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if ((stu.getName() != null) || (stu.getEmail() != null) || (stu.getGender() != 0) || (stu.getPhone() != null)) {
            if (existingStudent != null) {
                if (stu.getName() != null) {
                    existingStudent.setName(stu.getName());
                }
                if (stu.getEmail() != null) {
                    existingStudent.setEmail(stu.getEmail());
                }
                if (stu.getPhone() != null) {
                    existingStudent.setPhone(stu.getPhone());
                }
                if (stu.getGender() != 0) {
                    existingStudent.setGender(stu.getGender());
                }
                studentRepository.save(existingStudent);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Student partially updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("field requieed");
        }
    }

    public ResponseEntity<String> deleteStudent(Long id) {
        Student existStudent = studentRepository.findById(id).orElse(null);

        if (existStudent != null) {
            studentRepository.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted sucessfully with id = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id = " + id);
        }
    }
}
