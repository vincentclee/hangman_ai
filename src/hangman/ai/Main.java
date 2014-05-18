package hangman.ai;

/**
 * Hangman AI
 * 
 * @author Vincent Lee
 * @since April 18, 2014
 * @version 1.0
 */

import java.util.regex.Pattern;

public class Main {
	
	public Main() {
		
	}
	
	public void regexTest(String partialWord) {
//		String regex = "a.c.e.";
		String regex = partialWord.replaceAll("-", ".");
		String input = "abcdef";
		
		System.out.println(regex);
		
		boolean match = Pattern.matches(regex, input);
		System.out.println(match);
		
//		new HangmanAI().makeGuess("b", "");
	}
	
	public static void main(String[] args) {
//		new HangmanAII();
//		HangmanAI test = new HangmanAI();
//		new Main().regexTest("a-c-e-");
		
//		MyHangmanAI test = new MyHangmanAI();
//		System.out.println(test.makeGuess("----", ""));
//		System.out.println(test.makeGuess("----", "e"));
//		System.out.println(test.makeGuess("aa--", "ea"));
//		System.out.println(test.makeGuess("aals", "eas"));
		
		new MyGuesser(true).guess("aals");
	}
}
