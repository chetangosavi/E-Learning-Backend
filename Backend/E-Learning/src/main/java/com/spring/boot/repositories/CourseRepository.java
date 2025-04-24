package com.spring.boot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.entity.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
	
	 List<Course> findByApproved(boolean isApproved);
}
