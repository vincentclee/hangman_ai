package hangman.ai;

/**
 * Hangman AI
 * 
 * @author Vincent Lee
 * @since April 18, 2014
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HangmanAII {
	private static final boolean DEBUG = false;
	private static final String DICTIONARY = "dictionary.data";
	private Map<Integer, List<String>> dictionaryMap;
	
	/**
	 * Default Constructor
	 */
	public HangmanAII() {
		this.dictionaryMap = new HashMap<Integer, List<String>>();
		
		//Add dictionary file to length map
		loadDictionary();
	}
	
	/**
	 * Responsibilities are to open up dictionary.data (which must be in the same directory as your hangman program)
	 */
	private void loadDictionary() {
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
		
		//Load each line(word) into map based on word length
		while (scanner.hasNext()) {
			String word = scanner.next();
			
			if (!dictionaryMap.containsKey(word.length()))
				dictionaryMap.put(word.length(), new ArrayList<String>());
			dictionaryMap.get(word.length()).add(word);
		}
		
		if (DEBUG) mapLengthPrinter();
	}
	
	private void mapLengthPrinter() {
		Iterator<Map.Entry<Integer, List<String>>> entries = dictionaryMap.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<Integer, List<String>> entry = entries.next();
		    System.out.println(entry.getKey() + "=[" + entry.getKey() + "]");
		}
		System.out.println();
	}
	
	/**
	 * Generates a guess based on all available knowledge
	 * @param word the word with blanks (lower case letters, blanks are -)
	 * @param guessed string of guessed letters
	 * @return Guess letter
	 */
	public char makeGuess(String word, String guessed) {
		return 'a';
	}
}
