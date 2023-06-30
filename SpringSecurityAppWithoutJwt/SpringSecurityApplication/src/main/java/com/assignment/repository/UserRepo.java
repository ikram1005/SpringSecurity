package com.assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.model.Student;

public interface UserRepo extends JpaRepository<Student, Integer>{

	Optional<Student> findByEmail(String email);
}
