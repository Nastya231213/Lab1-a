package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.GameDAO;
import com.entity.Game;

public class GameDAOTest {
	private Connection conn;
	private GameDAO gameDAO;

	@Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		gameDAO = new GameDAO(conn);

	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	@Test
	public void testInsertGame() {
		Game game = new Game("Test Game", 19.99, 1, "test_game.jpg", 1, "This is a test game", "Test Developer");

		boolean result = gameDAO.insertGame(game);
		assertEquals(true, result);
	}

	@Test
	public void testGetAllGames() {
		List<Game> games = gameDAO.getAllGames();
		assertNotNull(games);

	}

	@Test
	public void testGetGamesBySearchCat() {
		String keyword = "Test";
		int category_id = 1;
		int minPrice = 0;
		int maxPrice = 100;

		List<Game> gamesBySearchCat = gameDAO.getGamesBySearchCat(keyword, category_id, minPrice, maxPrice);
		assertNotNull(gamesBySearchCat);
	}

	@Test
	public void testGetGameById() {
		Game game=gameDAO.getLastGame();
		Game foundGame= gameDAO.getGameById(game.getId());
		assertEquals(game.getTitle(),foundGame.getTitle());

	}
}
