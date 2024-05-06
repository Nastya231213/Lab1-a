package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

public class UserDAOTest {
	private Connection conn;
	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		userDAO = new UserDAO(conn);

	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
	
	@Test
	public void testInsertNewUser() {
		User user = new User("username1", "email@example.com", "password", "Full Name", "1234567890", "Address",
				"2022-04-21");
		userDAO.insert(user);
		assertNotNull(userDAO.getLastUser());
	}


	@Test
	public void testGetLastUser() {
		assertNotNull(userDAO.getLastUser());
	}

	@Test
	public void testUserLogin() {
		assertNotNull(userDAO.isExistAccount("email2@example.com", "password"));

		assertNull(userDAO.isExistAccount("nonexistent@example.com", "incorrectpassword"));
	}

	@Test
	public void testEmailExistenceCheck() {
		assertTrue(userDAO.isEmailExist("email@example.com"));
	}
	@Test

	public void testUpdateNewUser() {

		User userToUpdate = userDAO.getLastUser();
		userToUpdate.setUsername("newUsername");
	
		userDAO.update(userToUpdate);
		User updatedLastUser= userDAO.getLastUser();

	    assertEquals("newUsername", updatedLastUser.getUsername());
	}
	@Test

	public void testDeleteUser() {

	    User lastUser = userDAO.getLastUser();
	    assertNotNull(lastUser); 

	    int lastUserId = lastUser.getUser_id();

	    assertTrue(userDAO.delete(lastUserId));

	    assertNull(userDAO.getById(lastUserId));
	}
}
