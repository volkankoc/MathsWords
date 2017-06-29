package com.homestudio.mathswords.games;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameFactoryTest {

	@Test
	public void testResultMathGame() {
		Game game = GameFactory.parseGame("10+5=?");
		assertTrue(game.evaluate("15"));
		assertFalse(game.evaluate("14"));

		game = GameFactory.parseGame("10-5=?");
		assertTrue(game.evaluate("5"));
		assertFalse(game.evaluate("4"));

		game = GameFactory.parseGame("10*5=?");
		assertTrue(game.evaluate("50"));
		assertFalse(game.evaluate("40"));

		game = GameFactory.parseGame("10/5=?");
		assertTrue(game.evaluate("2"));
		assertFalse(game.evaluate("1"));
	}

	@Test
	public void testOperand1MathGame() {
		Game game = GameFactory.parseGame("?+5=15");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("14"));

		game = GameFactory.parseGame("?-5=5");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("4"));

		game = GameFactory.parseGame("?*5=50");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("40"));

		game = GameFactory.parseGame("?/5=2");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("1"));
	}

	@Test
	public void testOperand2MathGame() {
		Game game = GameFactory.parseGame("10+?=15");
		assertTrue(game.evaluate("5"));
		assertFalse(game.evaluate("14"));

		game = GameFactory.parseGame("15-?=5");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("4"));

		game = GameFactory.parseGame("5*?=50");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("40"));

		game = GameFactory.parseGame("20/?=2");
		assertTrue(game.evaluate("10"));
		assertFalse(game.evaluate("1"));
	}

	@Test
	public void testOperatorMathGame() {
		Game game = GameFactory.parseGame("10?5=15");
		assertTrue(game.evaluate("+"));
		assertFalse(game.evaluate("-"));

		game = GameFactory.parseGame("15?10=5");
		assertTrue(game.evaluate("-"));
		assertFalse(game.evaluate("/"));

		game = GameFactory.parseGame("5?10=50");
		assertTrue(game.evaluate("*"));
		assertFalse(game.evaluate("-"));

		game = GameFactory.parseGame("20?10=2");
		assertTrue(game.evaluate("/"));
		assertFalse(game.evaluate("*"));
	}

}
