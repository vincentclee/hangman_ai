/*
 * Copyright (c) 2014, vincentclee <ssltunnelnet@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package hangman.ai;

/**
 * MyGuesser for initializing AI and providing accuracy of the AI for a word.
 * 
 * @author Vincent Lee
 * @since May 18, 2014
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MyGuesser {
	private static final String DICTIONARY = "dictionary.data";
	private boolean console;
	private AI selector;
	private Object obj;
	private Method method;
	@SuppressWarnings("rawtypes")
	private Class cls;
	
	@SuppressWarnings("unchecked")
	public MyGuesser(AI selector, boolean console) {
		this.console = console;
		this.selector = selector;
		
		try {
			@SuppressWarnings("rawtypes")
			Class[] paramString = new Class[2];	
			paramString[0] = String.class;
			paramString[1] = String.class;
			
			switch (selector) {
				case PLAUE:
					cls = Class.forName("HangmanAI");
					break;
				case DECOMPILED:
					cls = Class.forName("hangman.decompiled.HangmanAI");
					break;
				case MINE:
					cls = Class.forName("hangman.ai.MyHangmanAI");
					break;
			}
			obj = cls.newInstance();
			
			method = cls.getDeclaredMethod("makeGuess", paramString);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			char guess = 0;
			try {
				guess = (char) method.invoke(obj, active.toString(), guessed);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (console) System.out.println("Guess: " + guess);
			
			//If the guess is not in the word, automatic miss
			if (!word.contains("" + guess))
				missed += guess;
			//Replace the correct letter in all occurances of the active string
			else {
				for (int i = 0; i < word.length(); i++)
					if (word.charAt(i) == guess)
						active.replace(i, i+1, "" + guess);
			}
			
			//All letters are guessed letters
			guessed += guess;
			if (console) System.out.println();
		}
		if (console) System.out.println("Word: " + active);
		if (console) System.out.println("Missed: " + missed);
		
		return (guessed.length() - missed.length()) / (guessed.length() + 0.0);
	}
	
	public double guessAll() {
		//Open File and load into Scanner
		File filename = null;
		Scanner scanner = null;
		try {
			filename = new File(DICTIONARY);
			scanner = new Scanner(filename);
		} catch (FileNotFoundException localFileNotFoundException) {
			System.err.println("Error: Couldn't find " + DICTIONARY);
			System.exit(1);
		}
		
		//Accuracy
		double accuracy = 0.0;
		//Words
		int words = 0;
		//Guess all words in dictionary
		while (scanner.hasNext()) {
			String word = scanner.next();
			
			accuracy =+ guess(word);
			words++;
			try {
				obj = cls.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.print(words);
			System.out.println(" " + (accuracy / (words + 0.0)));
			log(word, accuracy);
		}
		scanner.close();
		return accuracy / (words + 0.0);
	}
	
	public void log(String word, double accuracy) {
		try {
			File log = new File("accuracy_" + selector + ".log");
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			out.append(word);
			out.append(" ");
			out.append(Double.toString(accuracy));
			out.append("\n");
		    out.close();
		} catch(Exception e) {
			System.err.println("error: accuracy.log");
		}
	}
}
