package com.crs.lt.restcontroller;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crs.lt.dao.RegistrationDaoImplementation;
import com.crs.lt.model.Student;
import com.crs.lt.model.User;

@RestController
@CrossOrigin
@RequestMapping("/api/registration")
public class RegistrationRestController {
	private static final Logger LOGGER = getLogger(RegistrationRestController.class);
	
	@Autowired
	RegistrationDaoImplementation daoImplementation;
	
	 @PutMapping("/add-user")
	public void addUser(@RequestBody User user) {
		 try {
	            daoImplementation.addUser(user);
	            System.out.println("user Successfully added");
	        } catch (Exception e) {
	            e.printStackTrace();
	            LOGGER.error("Unable to add user");

	        }
		
	}
	 @PutMapping("/add-student")
	 public void addStudent(@RequestBody Student student) {
		 try {
	            daoImplementation.addStudent(student);
	            System.out.println("student Successfully added");
	        } catch (Exception e) {
	            e.printStackTrace();
	            LOGGER.error("Unable to add student");

	        }
	 }
	 @RequestMapping(value="/update-password/{userId}", method=RequestMethod.POST, consumes=MediaType.TEXT_PLAIN_VALUE)
	 public boolean updatePassword(@PathVariable int userId, @RequestBody String password) {
		 try {
	            daoImplementation.updatePassword(userId, password);
	            System.out.println("password changed");
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            LOGGER.error("Unable to change password");

	        }
		return false;
		 
	 }

}
