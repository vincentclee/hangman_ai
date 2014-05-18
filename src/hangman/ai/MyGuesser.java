package hangman.ai;

/**
 * MyGuesser for initializing AI and providing accuracy of the AI for a word.
 * 
 * @author Vincent Lee
 * @since May 18, 2014
 * @version 1.0
 */

public class MyGuesser {
	private MyHangmanAI ai;
	private boolean console;
	
	public MyGuesser(boolean console) {
		this.console = console;
		this.ai = new MyHangmanAI();
	}
	
	public double guess(String word) {
		//The string with the dashes
		StringBuilder active = new StringBuilder(word.replaceAll(".", "-"));
		//The missed character
		String missed = "";
		//The characters guessed and missed
		String guessed = "";
		
		//Run untill word is unveiled
		while (active.toString().contains("-")) {
			if (console) System.out.println("Word: " + active);
			if (console) System.out.println("Missed: " + missed);
			
			//Get the AI's guess
			char guess = ai.makeGuess(active.toString(), guessed);
			if (console) System.out.println("Guess: " + guess);
			
			//If the guess is not in the word, automatic miss
			if (!word.contains("" + guess))
				missed += guess;
			//Replace the correct letter in all occurances of the active string
			else
				for (int i = 0; i < word.length(); i++)
					if (word.charAt(i) == guess)
						active.replace(i, i+1, "" + guess);
			
			//All letters are guessed letters
			guessed += guess;
			if (console) System.out.println();
		}
		if (console) System.out.println("Word: " + active);
		if (console) System.out.println("Missed: " + missed);
		
		//Calculate Accuracy
		if (missed.length() >= word.length())
			System.err.println("ERROR");
		
		double accuracy = (word.length() - missed.length()) / (word.length() + 0.0);
		if (console) System.out.println("Accuracy: " + accuracy);
		
		return accuracy;
	}
}
