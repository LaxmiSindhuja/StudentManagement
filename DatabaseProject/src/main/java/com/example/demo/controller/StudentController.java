package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interfaces.StudentRepository;
import com.example.demo.repo.Student;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/getAllStudent")
	@CrossOrigin(origins = "*")
	public List<Student> getStudentDetails() {
		return studentRepository.findAll();
	}

	@GetMapping(value = "/getStudentById/{Id}")
	@CrossOrigin()
	public Student GetStudById(@PathVariable int Id, Student s) {
		Student std = studentRepository.findById(Id).orElse(new Student());
		return std;
	}

	@GetMapping("/student/{id}")
	@CrossOrigin()
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") int StudentId)
			throws ResourceNotFoundException {
		Student student = studentRepository.findById(StudentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + StudentId));

		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/addstudent")
	@CrossOrigin()
	public Student createStudent(@Valid @RequestBody Student student) {
		System.out.println("Student is " + student.getId());
		System.out.println("Student is " + student.getName());
		return studentRepository.save(student);
	}

	@PutMapping("/updatestudent/{id}")
	@CrossOrigin()
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") int studentId,
			@Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

		student.setId(studentDetails.getId());
		student.setName(studentDetails.getName());

		final Student updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/deletestudent/{id}")
	@CrossOrigin()
	public Map<String, Boolean> deleteStudentDetails(@PathVariable(value = "id") int studentId)
			throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + studentId));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/*
	 * @DeleteMapping("/deletingStudent/{id}") public void
	 * DeleteStudent(@PathVariable int id) { Student
	 * st=studentRepository.getOne(id); studentRepository.delete(st); return st;
	 * 
	 * }
	 * 
	 * @PutMapping("/UpdateStudent/{id}") public Student
	 * UpdatingStudent(@RequestBody Student s) { studentRepository.save(s); return
	 * s; }
	 */

}