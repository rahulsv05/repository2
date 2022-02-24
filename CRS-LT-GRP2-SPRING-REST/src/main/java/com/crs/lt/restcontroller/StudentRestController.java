package com.crs.lt.restcontroller;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.dao.StudentDaoImplementation;
import com.crs.lt.model.Course;
import com.crs.lt.model.Grade;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentRestController {
private static final Logger LOGGER = getLogger(StudentRestController.class);
	
	@Autowired
	StudentDaoImplementation daoImplementation;
	
	@GetMapping("/view-courses/{studentId}")
	public List<Course> viewCourses(@PathVariable int studentId){
		try {
			return daoImplementation.viewCourses(studentId);
		} catch (Exception e) {
	        e.printStackTrace();
	        LOGGER.error("Unable to view Course ");
	    }
		return null;
		
	}
	
	 @DeleteMapping("/delete_coures/{courseCode}/{studentId}")
	public boolean dropCourse(@PathVariable String courseCode, @PathVariable int studentId) {
		 try {
			 System.out.println("dropped");
		 return daoImplementation.dropCourse(courseCode, studentId);
		 
		 } catch (Exception e) {
		        e.printStackTrace();
		        LOGGER.error("Unable to drop Course ");
		    }
		 return false;
		
	
		}
	 
	 @PutMapping("/add-courses/{courseCode}/{studentId}")
	 public boolean addCourse(@PathVariable String courseCode, @PathVariable int studentId) {
		 try {
			 System.out.println("added");
			 return daoImplementation.addCourse(courseCode, studentId);
			
			 } catch (Exception e) {
			        e.printStackTrace();
			       LOGGER.error("Unable to add Course ");
			    }
		 return false;
	 }
	 
	 @GetMapping("/view-reg-courses/{studentId}")
		public List<Course> viewRegisteredCourses(@PathVariable int studentId){
			try {
				return daoImplementation.viewRegisteredCourses(studentId);
			} catch (Exception e) {
		        e.printStackTrace();
		        LOGGER.error("Unable to view Course ");
		    }
			return null;
			
		}
	 @GetMapping("/view-gradeCard/{studentId}")
	 public List<Grade> viewGradeCard(@PathVariable int studentId){
		 try {
				return daoImplementation.viewGradeCard(studentId);
			} catch (Exception e) {
		        e.printStackTrace();
		        LOGGER.error("Unable to view grade card ");
		    }
		 
		return null;
		 
	 }
	 @GetMapping("/make-payment/{studentId}")
	 public boolean makePayment(@PathVariable int studentId) {
		 try {
				return daoImplementation.makePayment(studentId);
			} catch (Exception e) {
		        e.printStackTrace();
		        LOGGER.error("Unable to view grade card ");
		    }
		return false;
		 
	 }
	}
	
	
	
	


