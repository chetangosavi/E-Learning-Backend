package com.spring.boot.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.boot.entity.User;


public interface AuthRepository extends MongoRepository<User, String> {

	User findUserByEmail(String email);
	Optional<User> findByEmail(String email);
}
