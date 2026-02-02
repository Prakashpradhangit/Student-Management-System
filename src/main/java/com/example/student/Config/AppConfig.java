package com.example.student.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    // @Bean
    // UserDetailsService userDetailsService(){
    //     UserDetails admin = User.withUsername("Admin")
    //                     .password(passwordEncoder().encode("admin"))
    //                     .roles("ADMIN")
    //                     .build();
    //     UserDetails teacher = User.withUsername("Teacher")
    //                     .password(passwordEncoder().encode("teacher"))
    //                     .roles("TEACHER")
    //                     .build();
    //     UserDetails student = User.withUsername("Student")
    //                     .password(passwordEncoder().encode("student"))
    //                     .roles("STUDENT")
    //                     .build();
    //     UserDetails department = User.withUsername("department")
    //                     .password(passwordEncoder().encode("department"))
    //                     .roles("DEPARTMENT")
    //                     .build();

    //     return new InMemoryUserDetailsManager(admin, teacher, student, department);
    // }
}
