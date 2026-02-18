# Student Management System API

Spring Boot REST API for managing students, teachers, departments, subjects, and authentication (JWT + OAuth2).

## Tech Stack
- Java 21
- Spring Boot 4.0.2
- Spring Web MVC
- Spring Data JPA (Hibernate)
- Spring Security + OAuth2 Client
- JWT (`jjwt`)
- MySQL
- Lombok

## Project Structure
- `src/main/java/com/example/student/Controller` - REST endpoints
- `src/main/java/com/example/student/Service` - business logic
- `src/main/java/com/example/student/Repository` - JPA repositories
- `src/main/java/com/example/student/Entity` - JPA entities
- `src/main/java/com/example/student/Security` - JWT/OAuth2 security setup
- `src/main/resources/application.properties` - app and DB config
- `src/main/resources/Application.yml` - OAuth2 client config

## Prerequisites
- JDK 21
- Maven (or use `./mvnw` / `mvnw.cmd`)
- MySQL running locally

## Configuration
Current defaults in `src/main/resources/application.properties`:
- Context path: `/api/v1`
- DB URL: `jdbc:mysql://localhost:33061/student_db`
- DB user: `root`
- JWT secret configured via `jwt.secrectKey`

Current OAuth2 client entries are in `src/main/resources/Application.yml` (`google`, `github`).

Recommended for local setup:
1. Create MySQL schema `student_db`.
2. Update DB username/password in `application.properties`.
3. Replace JWT secret with your own strong secret.
4. Replace OAuth client IDs/secrets with your own app credentials.

## Run the Project
Using Maven wrapper:
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

Build:
```bash
mvnw.cmd clean package
```

Test:
```bash
mvnw.cmd test
```

Base URL after startup:
- `http://localhost:8080/api/v1`

## Authentication Service - Spring Boot
- JWT is expected in header: `Authorization: Bearer <token>`
- `/auth/**` is public.
- Role based access:
  - `/admin/**` -> `ADMIN`
  - `/department/**` -> `ADMIN`
  - `/teacher/**` -> `ADMIN`, `TEACHER`
  - `/student/**` -> `ADMIN`, `TEACHER`, `STUDENT`


This module provides authentication and authorization functionality for the Student Management System. It supports both Email/Password login and OAuth2 login (Google, GitHub, etc.) with JWT token generation.

---

## Features

- User Signup with Email and Password
- Secure Password Encryption using PasswordEncoder
- JWT Token Generation for Authentication
- OAuth2 Login Support (Google, GitHub, etc.)
- Automatic Student Profile Creation during Signup
- Role-based User Management
- Fetch All Users API Support
- ModelMapper Integration for DTO conversion

---

JWT token is returned by login/signup OAuth success flows.

## API Endpoints
All routes are relative to `/api/v1`.

### Auth
- `POST /auth/signup`
- `POST /auth/login`

Sample signup request:
```json
{
  "username": "student1@example.com",
  "password": "strongPassword",
  "name": "Student One",
  "roles": ["STUDENT"]
}
```

Sample login request:
```json
{
  "username": "student1@example.com",
  "password": "strongPassword"
}
```
Email Login Response

- Username and Password authenticated using AuthenticationManager
- JWT Token generated
- Token returned to client

Response Example:

```json
{
  "id": 1,
  "username": "prakash@gmail.com",
  "token": "jwt-token"
}
````

### Student
- `GET /student`
- `GET /student/{id}`
- `POST /student`
- `PUT /student/{id}`
- `PATCH /student/{id}`
- `DELETE /student/{id}`

### Teacher
- `GET /teacher`
- `POST /teacher`

### Department
- `GET /department`
- `POST /department`

### Subject
- `GET /subject`
- `POST /subject`

### Admin
- `GET /admin/student`
- `GET /admin/teacher`
- `GET /admin/user`
- `POST /admin/newteacher`

## Core Domain Model
- `User` - authentication identity, provider info, and roles
- `Student` - linked one-to-one with `User`, belongs to a `Department`, can enroll in many `Subject`s
- `Teacher` - linked one-to-one with `User`, belongs to a `Department`, linked to a `Subject`
- `Department` - has many students, teachers, subjects
- `Subject` - belongs to a department


## Notes
- File naming has mixed casing for config (`Application.yml` + `application.properties`); keep this in mind if moving environments.
- Current unit test (`StudentApplicationTests`) depends on database state.
