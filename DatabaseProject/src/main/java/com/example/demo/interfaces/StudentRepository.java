package com.example.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repo.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	

}
