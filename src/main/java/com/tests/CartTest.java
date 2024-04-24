package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import com.entity.Cart;
import com.entity.Game;

public class CartTest {

	@Test
	public void testAddItem() {
		Game game1 = new Game("Game 1", 19.99, 1, "test_game.jpg", 1, "This is a test game", "Test Developer");
		Game game2 = new Game("Game 2", 20.00, 1, "test_game.jpg", 1, "This is a test game", "Test Developer");
		Cart cart = createCartWithGames(game1, game2);

		assertEquals(2, cart.getItems().size());
		assertEquals(2, (int) cart.getItems().get(game1));
		assertEquals(1, (int) cart.getItems().get(game2));
	}

	@Test
	public void testDeleteItem() {
		Game game1 = new Game("Game 1", 19.99, 1, "test_game.jpg", 1, "This is a test game", "Test Developer");
		Game game2 = new Game("Game 2", 20.00, 1, "test_game.jpg", 1, "This is a test game", "Test Developer");
		Cart cart = createCartWithGames(game1, game2);

		assertEquals(2, cart.getItems().size());
		assertEquals(2, (int) cart.getItems().get(game1));

		cart.deleteItem(game1.getId());

		assertEquals(1, cart.getItems().size());
		assertNull(cart.getItems().get(game1));
	}

	public Cart createCartWithGames(Game game1, Game game2) {
		Cart cart = new Cart();
		
		cart.addItem(game1);
		cart.addItem(game2);
		cart.addItem(game1);
		return cart;
	}
}
