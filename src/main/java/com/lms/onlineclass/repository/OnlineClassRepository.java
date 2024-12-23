package com.lms.onlineclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.onlineclass.model.OnlineClass;

@Repository
public interface OnlineClassRepository extends JpaRepository<OnlineClass, Integer> {
    
}