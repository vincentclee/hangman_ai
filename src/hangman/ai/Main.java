package hangman.ai;

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
		new Main().regexTest("a-c-e-");
		
		MyHangmanAI test = new MyHangmanAI();
		System.out.println(test.makeGuess("aalii", "iah"));
	}
}
