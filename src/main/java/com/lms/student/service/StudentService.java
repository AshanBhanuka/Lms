package com.lms.student.service;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.student.model.Student;
import com.lms.student.repository.StudentRepository;



@Service
public class StudentService {
	@Autowired
    private StudentRepository studentRepository;
	
	public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

     public Student getStudentByUserId (Integer userId) {
    	 return studentRepository.findByUserId(userId);
     }
     
     public Student updateStudent(Integer userId, Student updatedStudent) {
    	 updatedStudent.setUserId(userId);
    	 return studentRepository.save(updatedStudent);
     }
}

