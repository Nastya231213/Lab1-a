package com.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.PlatformDAO;
import com.entity.Platform;

public class PlatformDAOTest {
	private Connection conn;
    private PlatformDAO platformDAO;
    @Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		platformDAO= new PlatformDAO(conn);

	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
	@Test
	 public void testInsertPlatform() {
        // Insert a platform
        assertTrue("Platform insertion failed", platformDAO.insertPlatform("TestPlatform"));
    }
	 @Test
	    public void testGetAllPlatforms() {
	        List<Platform> platforms = platformDAO.getAllPlatforms();

	        assertNotNull("Returned platform list is null", platforms);
	        assertFalse("Returned platform list is empty", platforms.isEmpty());
	    }
	 
	  @Test
			public void testDeletPlatform() {

			   Platform lastPlatform = platformDAO.getLastPlatform();

			    int lastPlatformId =lastPlatform.getId();
	            
			    assertTrue(platformDAO.deletePlatform(lastPlatformId));

			    assertNull(platformDAO.getPlatformById(lastPlatformId));
	  }
}
