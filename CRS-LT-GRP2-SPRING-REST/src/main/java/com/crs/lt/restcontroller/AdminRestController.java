package com.crs.lt.restcontroller;

import com.crs.lt.dao.AdminDaoImplementation;
import com.crs.lt.model.Course;
import com.crs.lt.model.Professor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminRestController {

    private static final Logger LOGGER = getLogger(AdminRestController.class);

    @Autowired
    private AdminDaoImplementation adminDao;

    @GetMapping("/view-course")
    public List<Course> viewCoursesByCatalogId() throws SQLException {
        try {
            return adminDao.viewCoursesByCatalogId();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("Unable to delete Course ");
        }
		return null;
    }

    @DeleteMapping("/delete_coures/{courseCode}")
    public void deleteCourse(@PathVariable String courseCode) {
        try {
            adminDao.deleteCourse(courseCode);
            System.out.println("Course Successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to delete Course ");
        }
    }

    @PostMapping("/add-course")
    public void addCourse(@RequestBody Course course) {
        try {
        	//Course course2=new Course();
        	System.out.println(course.getName());
            adminDao.addCourse(course);
            System.out.println("Course Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to add Course");
        }
    }

    @PutMapping("/add-professor")
    public void addProfessor(@RequestBody Professor professor) {
        try {
            adminDao.addProfessor(professor);
            System.out.println("Professor Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to add professor");

        }
    }

    @PutMapping("/approve_Student/{studentId}")
    public void approveStudent(@PathVariable int studentId) {
        try {
            adminDao.approveStudent(studentId);
            System.out.println("Student is Successfully approved");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Student is not approved");
        }
    }

    @PutMapping("/assign_course/{professorId}/{courseCode}")
    public void assignCourse(@PathVariable String professorId, @PathVariable String courseCode) {
        try {
            adminDao.assignCourse(courseCode,professorId);
            System.out.println("Course Successfully Assigned");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Professor can't be assigned to given course");
        }
    }

}
