package com.lms.student.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.student.model.Student;
import com.lms.student.service.StudentService;



@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createStudent = studentService.createStudent(student);
		return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
	}

	@GetMapping("student/{userId}")
	public ResponseEntity<Student> getStudentByUserId(@PathVariable Integer userId) {
		Student student = studentService.getStudentByUserId(userId);
	    return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer userId, @RequestBody Student student) {
			Student updatedStudent = studentService.updateStudent(userId,  student);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		
		}
}

