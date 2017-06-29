package com.homestudio.mathswords.games;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultMathGame implements Game {

	public static String RESULT_MATH_PATTERN = "([0-9]+)([\\+\\-\\*\\/])([0-9]+)=\\?";
	private static String OUTPUT_FORMAT = "%d%s%d=?";
		
	public int operand1;
	public int operand2;
	public char operator;

	public ResultMathGame(){

		Random r = new Random();
		switch (r.nextInt(4)){
		case 0:
			operator = '+';
			operand1 = r.nextInt(900)+100;
			operand2 = r.nextInt(900)+100;
			break;
		case 1:
			operator = '-';
			operand1 = r.nextInt(900)+100;
			operand2 = r.nextInt(operand1-100)+100;
			break;
		case 2:
			operator = '*';
			operand1 = r.nextInt(30)+10;
			operand2 = r.nextInt(30)+10;
			break;
		case 3:
			operator = '/';
			operand2 = r.nextInt(30)+1;
			operand1 = (r.nextInt(30)+10)*operand2;
			break;
		}
	}
	
	public ResultMathGame (String str){
		Matcher matcher = Pattern.compile(RESULT_MATH_PATTERN).matcher(str);
		if (matcher.find()){
			operand1 = Integer.parseInt(matcher.group(1));
			operator = matcher.group(2).charAt(0);
			operand2 = Integer.parseInt(matcher.group(3));
		}
	}
	
	@Override
	public boolean evaluate(String answer) {

		boolean evaluation = false;
		switch (operator){
		case '+':
			evaluation = operand1 + operand2 == Integer.parseInt(answer);
			break;
		case '-':
			evaluation = operand1 - operand2 == Integer.parseInt(answer);
			break;
		case '*':
			evaluation = operand1 * operand2 == Integer.parseInt(answer);
			break;
		case '/':
			evaluation = operand1 / operand2 == Integer.parseInt(answer);
			break;
		default:
			throw new RuntimeException("Not valid operator");
		}
		return evaluation;
	}
	
	@Override
	public String toString(){
		return String.format(OUTPUT_FORMAT, operand1, operator, operand2);
	}

}
