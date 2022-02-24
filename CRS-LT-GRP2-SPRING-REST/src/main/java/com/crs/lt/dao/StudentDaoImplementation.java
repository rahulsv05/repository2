package com.crs.lt.dao;

import com.crs.lt.model.*;
import com.crs.lt.CRSApplication.ConfigurationJDBC;
import com.crs.lt.constant.SQLQueriesConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImplementation implements StudentDao {
	private static final Logger LOGGER = getLogger(StudentDaoImplementation.class);
	/*
	 * static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; static final
	 * String DB_URL = "jdbc:mysql://localhost:3306/crs";
	 * 
	 * static final String USER = "root"; static final String PASS = "password";
	 */
	@Autowired
	ConfigurationJDBC configurationJDBC;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;

	@Override
	public boolean addCourse(String courseCode, int studentId) throws SQLException {
		try {
			Course c = getAllCourseByCourseCode(courseCode);
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE_STUDENT);
			statement.setString(1, courseCode);
			statement.setString(2, c.getName());
			statement.setInt(3, studentId);
			statement.setString(4, null);
			statement.execute();
			

			return true;
		} catch (Exception e1) {
			LOGGER.error("Unable to add course");
		} finally {
			statement.close();
			connection.close();
		}
		return false;
	}

	@Override
	public boolean dropCourse(String courseCode, int studentId) throws SQLException {
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.DROP_COURSE_STUDENT);
			statement.setString(1, courseCode);
			statement.setInt(2, studentId);
			statement.execute();
			return true;
		} catch (Exception e1) {
			LOGGER.error("Unable to drop Course");
		} finally {
			statement.close();
			connection.close();
		}
		return false;
	}

	@Override
	public List<Course> viewCourses(int studentId) throws SQLException {
		List<Course> CourseList = new ArrayList<Course>();
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.VIEW_COURSES_ADMIN);
			rs = statement.executeQuery();
			while (rs.next()) {
				Course c1 = new Course();
				c1.setCourseCode(rs.getString("courseCode"));
				c1.setName(rs.getString("courseName"));
				CourseList.add(c1);
			}

			return CourseList;
		} catch (Exception e1) {
			LOGGER.error("No Course Available");
		} finally {
			statement.close();
			connection.close();
		}
		return CourseList;
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {
		List<Course> registeredCourses = new ArrayList<Course>();
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES_STUDENT);
			statement.setInt(1, studentId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Course c1 = new Course();
				c1.setCourseCode(rs.getString("courseCode"));
				c1.setName(rs.getString("courseName"));
				registeredCourses.add(c1);
			}

			return registeredCourses;
		} catch (Exception e1) {
			LOGGER.error("You have not registered to any course");
		} finally {
			statement.close();
			connection.close();
		}
		return registeredCourses;
	}

	@Override
	public List<Grade> viewGradeCard(int studentId) throws SQLException {
		List<Grade> gradelist = new ArrayList<Grade>();
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.VIEW_GRADE);
			statement.setInt(1, studentId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setCourseCode(rs.getString("courseCode"));
				grade.setCourseName(rs.getString("courseName"));
				grade.setGrade(rs.getString("Grade"));
				gradelist.add(grade);
			}
		} catch (Exception e1) {
			LOGGER.error("No Grade have been given to you");
		} finally {
			statement.close();
			connection.close();
		}

		return gradelist;

	}

	@Override
	public boolean makePayment(int studentId) throws SQLException {
		int amount = getTotalFee(studentId);
		try {

			System.out.println("in try");
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.PAYMENT_RECORD);
			statement.setInt(1, studentId);
			statement.setInt(2, amount);
			statement.setInt(3, 1);
			statement.execute();
			return true;
		} catch (Exception e1) {
			LOGGER.error("Unable to complete payment");
			e1.printStackTrace();
		} finally {
			statement.close();
			connection.close();
		}
		return false;

	}

	public int getTotalFee(int StudentId) throws SQLException {
		int total = 0;
		try {

			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.GET_TOTAL_FEE);
			statement.setInt(1, StudentId);
			rs = statement.executeQuery();
			while (rs.next()) {
				total = total + rs.getInt("fee");

			}

		} catch (Exception e1) {
			LOGGER.error("unable to get total fee");
		} finally {
			statement.close();
			connection.close();
		}
		return total;

	}

	public Course getAllCourseByCourseCode(String courseCode) throws SQLException {
		Course c1 = new Course();

		try {

			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.VIEW_COURSES_ALL);
			rs = statement.executeQuery();
			while (rs.next()) {

				c1.setCourseCode(rs.getString("courseName"));
				c1.setName(rs.getString("courseName"));
				c1.setInstructorId(String.valueOf(rs.getInt("instructorId")));
				c1.setSeats(rs.getInt("seat"));

			}

		} catch (Exception e1) {
			LOGGER.error("No Course Available");
		} finally {
			statement.close();
			connection.close();
		}
		return c1;

	}

}
