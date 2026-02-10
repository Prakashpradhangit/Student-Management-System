package com.example.student.DTO;

import com.example.student.Entity.Type.AuthProviderType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private AuthProviderType providerType;
    private String providerId;
    private String roles;
    


}
