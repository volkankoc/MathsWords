package com.homestudio.mathswords.games;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperatorMathGame implements Game {

	public static String OPERATOR_MATH_PATTERN = "([0-9]+)\\?([0-9]+)=([0-9]+)";
	private static String OUTPUT_FORMAT = "%d?%d=%d";

	public int operand1;
	public int operand2;
	public int result;
	
	public OperatorMathGame (String str){
		Matcher matcher = Pattern.compile(OPERATOR_MATH_PATTERN).matcher(str);
		if (matcher.find()){
			operand1 = Integer.parseInt(matcher.group(1));
			operand2 = Integer.parseInt(matcher.group(2));
			result = Integer.parseInt(matcher.group(3));
		}
	}
	
	@Override
	public boolean evaluate(String answer) {

		boolean evaluation = false;
		switch (answer.charAt(0)){
		case '+':
			evaluation = operand1 + operand2 == result;
			break;
		case '-':
			evaluation = operand1 - operand2 == result;
			break;
		case '*':
			evaluation = operand1 * operand2 == result;
			break;
		case '/':
			evaluation = operand1 / operand2 == result;
			break;
		default:
			throw new RuntimeException("Not valid operator");
		}
		return evaluation;
	}
	
	@Override
	public String toString(){
		return String.format(OUTPUT_FORMAT, operand1, operand2, result);
	}

}
