package com.example.student.Security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Incomming request", request.getRequestURI());

        final String requestTokenHeader = request.getHeader("Authorization");

        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Barrer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.split("Barrer")[1];
        
    }

    
}