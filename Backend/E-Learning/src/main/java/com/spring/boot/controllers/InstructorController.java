package com.spring.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.entity.Course;
import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;
import com.spring.boot.services.CourseService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private AuthRepository authRepository;
	
	//create
	@PostMapping("/create")
	public void createCourse(@RequestBody Course course) {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		courseService.saveCourse(course,username);
	}
	
	@GetMapping("/getallcourses")
	public ResponseEntity<List<Course>> getAllCoursesOfInstructor(){
		
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName(); 
			User user = authRepository.findUserByEmail(username);
			
			List<Course> allCourses = user.getCoursesCreated();
			
			if(allCourses != null && !allCourses.isEmpty()) {
				return new ResponseEntity<>(allCourses,HttpStatus.OK);
			}
			return new ResponseEntity<>(allCourses,HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//update
	@PutMapping("/update")
	public String updateCourse(@RequestBody Course course) {
		return "I am update course";
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public String deleteCourse(@PathVariable String id) {
		return "I am delete course";
	}
}
