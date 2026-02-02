package com.example.student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.Entity.User;

public interface UserRespository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

}
