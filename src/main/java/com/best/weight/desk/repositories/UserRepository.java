package com.best.weight.desk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.best.weight.desk.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public User findByEmail(String email);
}
