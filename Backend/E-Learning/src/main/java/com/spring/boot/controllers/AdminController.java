package com.spring.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.entity.Course;
import com.spring.boot.services.CourseService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private CourseService courseService;
	
	
	@PostMapping("/approve/{id}")
	public Course approveCourse(@PathVariable String id) {
        Course approvedCourse = courseService.approveCourse(id);
        return approvedCourse;
    }
}
