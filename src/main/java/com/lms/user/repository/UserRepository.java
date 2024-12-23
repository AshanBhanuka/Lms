package com.lms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUsername(String username);





	



	
}
