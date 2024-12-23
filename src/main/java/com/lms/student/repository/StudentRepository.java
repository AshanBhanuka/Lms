package com.lms.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.student.model.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByUserId(Integer userid);

}
