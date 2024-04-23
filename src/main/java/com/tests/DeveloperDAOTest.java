package com.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.DeveloperDAO;
import com.entity.Developer;


public class DeveloperDAOTest {
	private Connection conn;
    private DeveloperDAO developerDAO;
    @Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		developerDAO= new DeveloperDAO(conn);

	}
	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
	@Test
	public void testInsertDeveloper() {
        Developer developer = new Developer("Test Developer", 1);
        developerDAO.insertDeveloper(developer);

        assertEquals("Test Developer", developerDAO.getLast().getName());
    }
}
