package com.example.student.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.Entity.User;
import com.example.student.Entity.Type.AuthProviderType;

@Repository
public interface UserRespository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType authProviderType);

}
