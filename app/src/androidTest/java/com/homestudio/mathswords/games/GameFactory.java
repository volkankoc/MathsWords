package com.homestudio.mathswords.games;

import java.util.Random;

public class GameFactory {

	
	public static Game parseGame(String strGame){
		Game game = null;
		
		if (strGame.matches(ResultMathGame.RESULT_MATH_PATTERN)){
			game = new ResultMathGame(strGame);
		}else if (strGame.matches(Operand1MathGame.OPERAND1_MATH_PATTERN)){
			game = new Operand1MathGame(strGame);
		}else if (strGame.matches(Operand2MathGame.OPERAND2_MATH_PATTERN)){
			game = new Operand2MathGame(strGame);
		}else if (strGame.matches(OperatorMathGame.OPERATOR_MATH_PATTERN)){
			game = new OperatorMathGame(strGame);			
		}
		
		return game;
	}
	
	public static Game createMathGame()
	{
		Game game = null;
		
		Random r = new Random();
		switch (r.nextInt(1)){
		case 0:
			game = new ResultMathGame();
			break;
		}
		
		return game;
	}
}
