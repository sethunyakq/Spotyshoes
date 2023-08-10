package com.example.Phase3_Project.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Phase3_Project.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//custom query methods
	List<User> findByUsername(String username);
	
	User findByEmail(String email);

}

