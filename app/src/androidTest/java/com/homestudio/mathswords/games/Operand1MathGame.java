package com.homestudio.mathswords.games;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operand1MathGame implements Game {
	
	public static String OPERAND1_MATH_PATTERN = "\\?([\\+\\-\\*\\/])([0-9]+)=([0-9]+)";
	private static String OUTPUT_FORMAT = "?%s%d=%d";

	public int operand2;
	public char operator;
	public int result;
	
	public Operand1MathGame (String str){
		Matcher matcher = Pattern.compile(OPERAND1_MATH_PATTERN).matcher(str);
		if (matcher.find()){
			operator = matcher.group(1).charAt(0);
			operand2 = Integer.parseInt(matcher.group(2));
			result = Integer.parseInt(matcher.group(3));
		}
	}
	
	@Override
	public boolean evaluate(String answer) {

		boolean evaluation = false;
		switch (operator){
		case '+':
			evaluation = Integer.parseInt(answer) + operand2 == result;
			break;
		case '-':
			evaluation = Integer.parseInt(answer) - operand2 == result;
			break;
		case '*':
			evaluation = Integer.parseInt(answer) * operand2 == result;
			break;
		case '/':
			evaluation = Integer.parseInt(answer) / operand2 == result;
			break;
		default:
			throw new RuntimeException("Not valid operator");
		}
		return evaluation;
	}
	
	@Override
	public String toString(){
		return String.format(OUTPUT_FORMAT, operator, operand2, result);
	}

}
