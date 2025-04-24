package com.spring.boot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.entity.Course;
import com.spring.boot.entity.User;
import com.spring.boot.repositories.AuthRepository;
import com.spring.boot.repositories.CourseRepository;



@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private AuthService authService;
	
	
	@Transactional
	public ResponseEntity<?> saveCourse(Course course, String username) {
		
		try {
			User user = authRepository.findUserByEmail(username);
			course.setDate(LocalDateTime.now());
			courseRepository.save(course);
			user.getCoursesCreated().add(course);
			authRepository.save(user);
			return new ResponseEntity<>("Course Created",HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Transactional
	public ResponseEntity<?> purchaseCourse(String id,String username){
		try {
			User user = authRepository.findUserByEmail(username);
			return new ResponseEntity<>("Course Purchased Successfully",HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<Course> allCoursesOfInstructor(){
		return courseRepository.findAll();
	}
	
	public Optional<Course> getById(String id) {
		return courseRepository.findById(id); 
	}
	
	public  Course  approveCourse(String courseId) {
		
		Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setApproved(true);
        return courseRepository.save(course);
	}
	
	public List<Course> getAllApprovedCourses(){
		List<Course> approvedCourseList =  courseRepository.findByApproved(true);
		return approvedCourseList;
	}
	
	
	@Transactional
	public void deleteById(String id, String email) {
		User user =  authRepository.findUserByEmail(email);
		user.getCoursesCreated().removeIf(x->x.getId().equals(id));
		authService.saveUser(user);
		courseRepository.deleteById(id);
	}

}
