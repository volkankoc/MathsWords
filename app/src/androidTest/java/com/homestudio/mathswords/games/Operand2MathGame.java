package com.homestudio.mathswords.games;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operand2MathGame implements Game {
	
	public static String OPERAND2_MATH_PATTERN = "([0-9]+)([\\+\\-\\*\\/])\\?=([0-9]+)";	
	private static String OUTPUT_FORMAT = "%d%s?=%d";

	public int operand1;
	public char operator;
	public int result;
	
	public Operand2MathGame (String str){
		Matcher matcher = Pattern.compile(OPERAND2_MATH_PATTERN).matcher(str);
		if (matcher.find()){
			operand1 = Integer.parseInt(matcher.group(1));
			operator = matcher.group(2).charAt(0);
			result = Integer.parseInt(matcher.group(3));
		}
	}
	
	@Override
	public boolean evaluate(String answer) {

		boolean evaluation = false;
		switch (operator){
		case '+':
			evaluation = operand1 + Integer.parseInt(answer) == result;
			break;
		case '-':
			evaluation = operand1 - Integer.parseInt(answer) == result;
			break;
		case '*':
			evaluation = operand1 * Integer.parseInt(answer) == result;
			break;
		case '/':
			evaluation = operand1 / Integer.parseInt(answer) == result;
			break;
		default:
			throw new RuntimeException("Not valid operator");
		}
		return evaluation;
	}
	
	@Override
	public String toString(){
		return String.format(OUTPUT_FORMAT, operand1, operator, result);
	}

}
