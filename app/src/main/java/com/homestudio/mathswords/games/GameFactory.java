package com.homestudio.mathswords.games;

import java.util.Random;

public class GameFactory {

	
	public static Game parseGame(String strGame){
		Game game = null;
		
		if (strGame.matches(ResultFastMathGame.RESULT_MATH_PATTERN)){
			game = new ResultFastMathGame(strGame);
		}else if (strGame.matches(Operand1FastMathGame.OPERAND1_MATH_PATTERN)){
			game = new Operand1FastMathGame(strGame);
		}else if (strGame.matches(Operand2FastMathGame.OPERAND2_MATH_PATTERN)){
			game = new Operand2FastMathGame(strGame);
		}else if (strGame.matches(OperatorFastMathGame.OPERATOR_MATH_PATTERN)){
			game = new OperatorFastMathGame(strGame);
		}
		
		return game;
	}
	
	public static Game createMathGame()
	{
		Game game = null;
		
		Random r = new Random();
		switch (r.nextInt(1)){
		case 0:
			game = new ResultFastMathGame();
			break;
		}
		
		return game;
	}
}
