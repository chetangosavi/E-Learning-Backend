package com.spring.boot.controllers;

import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.ATan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.entity.Course;
import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;
import com.spring.boot.repositories.CourseRepository;
import com.spring.boot.services.CourseService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/course/approved")
	public ResponseEntity<List<Course>> getAllApprovedCourses(){
		try {
			System.out.println("i M in");
			List<Course> courseList = courseRepository.findByApproved(true);
			System.out.println(courseList);
			return new ResponseEntity<List<Course>>(courseList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("/courses/purchased")
	public ResponseEntity<List<Course>> getAllPurchasedCourses(){
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User user = authRepository.findUserByEmail(username);
			List<Course> courseList = user.getCoursesPurchased();
			System.out.println(courseList);
			return new ResponseEntity<List<Course>>(courseList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/course/purchase/{id}")
	public void purchaseCourse(@PathVariable String  id){
	
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			courseService.purchaseCourse(id, username);
		
	}
	
	

}
