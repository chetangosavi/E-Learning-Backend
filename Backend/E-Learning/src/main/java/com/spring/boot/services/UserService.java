package com.spring.boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Course;
import com.spring.boot.repositories.CourseRepository;



@Service
public class UserService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses(){
		List<Course> allCourses = courseRepository.findAll();
		return allCourses;
	}
	
}
