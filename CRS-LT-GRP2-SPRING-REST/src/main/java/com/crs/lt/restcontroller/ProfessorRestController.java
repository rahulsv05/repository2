package com.crs.lt.restcontroller;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.dao.ProfessorDaoImplementation;
import com.crs.lt.model.Course;
import com.crs.lt.model.EnrolledStudent;

@RestController
@CrossOrigin
@RequestMapping("/api/professor")
public class ProfessorRestController {
	
	private static final Logger LOGGER = getLogger(ProfessorRestController.class);
	
	@Autowired
	ProfessorDaoImplementation daoImplementation;
	
	
	@GetMapping("/view-course/{professorId}")
	public List<Course> getCoursesByProfessorId(@PathVariable int professorId) throws SQLException{
		 try {
		return daoImplementation.getCoursesByProfessorId(professorId);
	} catch (Exception e) {
        e.printStackTrace();
        LOGGER.error("Unable to view Course ");
    }
		 return null;
	}
	
	@GetMapping("/view-reg-student/{professorId}")
	public List<EnrolledStudent> getEnrolledStudents(@PathVariable int professorId){
		try {
			return daoImplementation.getEnrolledStudents(professorId);
		} catch (Exception e) {
	        e.printStackTrace();
	        LOGGER.error("Unable to view enrolled student ");
	    }
		return null;
		
	}
	
	@PutMapping("/add-grade/{studentId}/{courseCode}/{grade}")
	public Boolean addGrade(@PathVariable int studentId,  @PathVariable String courseCode,@PathVariable String grade) {
		try {
			return daoImplementation.addGrade(studentId, courseCode, grade);
		} catch (Exception e) {
	        e.printStackTrace();
	        LOGGER.error("Unable to add grade ");
	    }
		return null;
		
	}
	
	
	

}
