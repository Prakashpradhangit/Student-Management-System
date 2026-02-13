package com.example.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OnBaordNewTeacher {
    private Long userId;
    private String name;
    private String specialization;

}
