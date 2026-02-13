package com.example.student.Service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.student.DTO.OnBaordNewTeacher;
import com.example.student.Entity.Teacher;
import com.example.student.Entity.Type.RoleType;
import com.example.student.Repository.TeacherRepository;
import com.example.student.Repository.UserRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRespository userRespository;

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public ResponseEntity<Teacher> createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);

    }

    @Transactional
    public Teacher onBoardNewTeacher(OnBaordNewTeacher onBaordNewTeacher) {
        com.example.student.Entity.User user = userRespository.findById(onBaordNewTeacher.getUserId()).orElseThrow();

        if(teacherRepository.existsById(onBaordNewTeacher.getUserId())){
            throw new IllegalArgumentException("User Alread a Teacher");
        }

        Teacher teacher = Teacher.builder()
                            .name(onBaordNewTeacher.getName())
                            .user(user)
                            .build();
        user.getRoles().add(RoleType.TEACHER);

        teacherRepository.save(teacher);

        return teacher;                

    }

}
