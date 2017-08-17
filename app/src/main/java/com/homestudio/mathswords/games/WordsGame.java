package com.homestudio.mathswords.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class WordsGame implements Game {

	public String letters;
	private Locale locale;
	public static final List<Character> CONSONANTS = new ArrayList<>(
	      Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z')
	  );
	  public static final List<Character> VOWELS = new ArrayList<>(
	      Arrays.asList(
	          'A', 'E', 'I', 'O', 'U'
	      )
	  );
	
	public WordsGame(Locale locale){
		Random r = new Random();
		this.letters="";
		for (int i=0; i<9;i++){
			if (i%2==0){
				this.letters+=getVowel();
			}else{
				this.letters+=getConsonant();
			}
			
		}
		this.locale=locale;
	}
	
	public WordsGame (String str, Locale locale){
		this.letters=str;
		this.locale=locale;
	}
	
	private char getVowel(){
		Random r = new Random();
		return VOWELS.get(r.nextInt(VOWELS.size()));
	}
	
	private char getConsonant(){
		Random r = new Random();
		return CONSONANTS.get(r.nextInt(CONSONANTS.size()));
	}
	
	
	private boolean searchWord(String word){
		boolean flag = false;
		File file = new File(locale.getLanguage() + "_words.txt");
		final Collator instance = Collator.getInstance(locale);

	    // This strategy mean it'll ignore the accents
	    instance.setStrength(Collator.NO_DECOMPOSITION);
				
		Scanner scanner;
		try {
			scanner = new Scanner(file,"UTF8");
			while (scanner.hasNext() && !flag) {
			    String nextToken = scanner.next();
			    if (instance.compare(nextToken, word)==0)
			    {
			    	flag = true;	
			    }
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean evaluate(String answer) {
		StringBuilder auxLetters = new StringBuilder(letters);
		int auxIndex;
		// Check word characters
		for (int i=0; i< answer.length(); i++){
			if ((auxIndex = auxLetters.indexOf(""+answer.charAt(i)))>=0){
				auxLetters.deleteCharAt(auxIndex);
			}
			else{
				return false;
			}
		}
		if (searchWord(answer)){
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return letters;
	}
	
}
