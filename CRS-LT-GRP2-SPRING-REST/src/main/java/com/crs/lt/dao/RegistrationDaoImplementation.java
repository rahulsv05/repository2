package com.crs.lt.dao;

import com.crs.lt.model.Student;
import com.crs.lt.model.User;
import com.crs.lt.CRSApplication.ConfigurationJDBC;
import com.crs.lt.constant.SQLQueriesConstants;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.apache.logging.log4j.Logger.*;

@Repository
public class RegistrationDaoImplementation implements RegistrationDao {
	private static final Logger LOGGER = getLogger(AdminDaoImplementation.class);
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
	public void addUser(User user) throws SQLException {
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER);
			statement.setInt(1, user.getUserId());
			statement.setString(2,user.getName());
			statement.setString(3, user.getRole());
			statement.setString(4, user.getPassword());
			statement.execute();
			

		} catch (Exception e1) {
			LOGGER.error("User is not added");
		} finally {
			statement.close();
			connection.close();
		}
		
	}

	@Override
	public void addStudent(Student student) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT);
			statement.setInt(1, student.getStudentId());
			statement.setString(2,student.getBranch());
			statement.setString(3, student.getBatch());
			statement.setInt(4, 0);
			statement.execute();
			

		} catch (Exception e1) {
			
			LOGGER.error("Student is not added");
			e1.printStackTrace();
		} finally {
			statement.close();
			connection.close();
		}
		
	}

	@Override
	public boolean updatePassword(int userId, String password) throws SQLException {
		try {
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.UPDATE_PASSWORD);
			statement.setString(1,password);
			statement.setInt(2, userId);
			statement.executeUpdate();
			return true;

		} catch (Exception e1) {
			LOGGER.error("Password not Updated");
		} finally {
			statement.close();
			connection.close();
		}
		return false;
	}

	@Override
	public User getUserDetails(int userId) throws SQLException {
		try {
			User user = new User();
			connection = configurationJDBC.dataSource().getConnection();
			statement = connection.prepareStatement(SQLQueriesConstants.GET_USER_DETAILS);
			statement.setInt(1, userId);
			rs=statement.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				user.setPassword(rs.getString("password"));
			}
			return user;

		} catch (Exception e1) {
			LOGGER.error("User is not present");
		} finally {
			statement.close();
			connection.close();
		}
		return null;
	}

   
}
