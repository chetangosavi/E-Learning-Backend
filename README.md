# 📚 E-Learning Platform Backend - Spring Boot

This is the backend service for an E-Learning Platform built using **Java Spring Boot**. It provides REST APIs for managing users, courses, instructors, students, modules, lessons, and quizzes. The system supports **role-based access**, **JWT authentication**, and a scalable architecture for modern learning platforms.

---

## 🚀 Features

- 🛡️ Role-based Authentication & Authorization (Admin, Instructor, Student)
- ✅ Secure login/signup with **JWT** and password encryption
- 📦 Modular Course and Content Management
- 🧑‍🏫 Instructor: Create & Manage Courses
- 🎓 Student: Browse, Enroll & Access Lessons/Quizzes
- 🧰 Admin Panel: Manage Users, Categories, and Roles
- 🌐 RESTful APIs
- 🗃️ MongoDB as the database (can be swapped for SQL)

---

## 🛠️ Tech Stack

- **Java 1.8+**
- **Spring Boot 3+**
- **Spring Security 6**
- **MongoDB**
- **JWT for authentication**
- **Lombok**
- **Maven**

---

## 🧾 API Endpoints Overview

> Full API documentation with Postman coming soon.

## Authentication :
- Login : POST - `http://localhost:${PORT}/elearning/api/auth/login`
- Signup : POST - `http://localhost:${PORT}/elearning/api/auth/signup`

### User:
- Get All Approved Courses : GET - `http://localhost:${PORT}/elearning/api/user/course/approved`
- Get All Purchased Courses : GET - `http://localhost:${PORT}/elearning/api/user/courses/purchased`

## Admin :
- Approve Course: POST - `http://localhost:${PORT}/elearning/api/admin/approve/id`

## Instructor :
- Create Course : POST - `http://localhost:${PORT}/elearning/api/instructor/create`
- Get All Courses of Instructor : GET - `http://localhost:${PORT}/elearning/api/instructor/create`


---

## 🔐 Authentication Flow

- Passwords are hashed using BCrypt.
- JWT is generated on successful login and passed in `Authorization: Bearer <token>` header.
- Role-based access is enforced using Spring Security annotations (`@PreAuthorize`, `@Secured`).


