package com.tests;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.GenreDAO;
import com.entity.Genre;


public class GenreDAOTest {
	private Connection conn;
	private GenreDAO genreDAO;

	@Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		genreDAO = new GenreDAO(conn);

	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
	@Test
    public void testInsertGenre() {
        // Insert a new genre
        boolean result = genreDAO.insertGenre("New Genre");
        assertTrue(result, "Genre insertion should return true");

        assertNotNull(genreDAO.getLastGenre(), "Genre 'New Genre' should exist in the database");
    }
	
	  @Test
	   public void testGetAllGenre() {
	        List<Genre> genres = genreDAO.getAllGenre();

	        assertNotNull(genres, "List of genres should not be null");
	        assertFalse(genres.isEmpty(), "List of genres should not be empty");
	    }
	  @Test
		public void testDeleteGenre() {

		   Genre lastGenre = genreDAO.getLastGenre();

		    int lastGenreId =lastGenre.getId();
            
		    assertTrue(genreDAO.deleteGenre(lastGenreId));

		    assertNull(genreDAO.getGenreById(lastGenreId));
		}
}
