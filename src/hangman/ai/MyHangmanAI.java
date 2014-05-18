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
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class MyHangmanAI {
	private static final boolean DEBUG = false;
	private static final String DICTIONARY = "dictionary.data";
	private Map<Integer, List<String>> dictionaryMap;
	private List<String> words;
	private Map<Character, Integer> characterMap;
	private SortedMap<Integer, List<Character>> frequencyMap;
	
	/**
	 * Default Constructor
	 */
	public MyHangmanAI() {
		this.dictionaryMap = new HashMap<Integer, List<String>>();
		this.characterMap = new HashMap<Character, Integer>();
		this.frequencyMap = new TreeMap<Integer, List<Character>>();
		
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
	
	public char makeGuessV1(String word, String guessed) {
		
		//V1 - Achieved
		/*
		 * Takes dictionaryMap and processes only ones with correct length.
		 * Regex those words and puts matches into words List.
		 */
		if (words == null) {
			words = new ArrayList<String>();
			String regex = word.replaceAll("-", ".");
			for (String str : dictionaryMap.get(word.length())) {
				if (Pattern.matches(regex, str))
					words.add(str);
			}
			
			//Garbage collection
			dictionaryMap = null;
		}
		
		//produces output map.
		System.out.println(words.toString());
		
		//Next step, count characters excluding matched, and guessed
		//Guessed should inlcude matched+missed characters
		
		return 'a';
	}
	
	/**
	 * Generates a guess based on all available knowledge
	 * @param word the word with blanks (lower case letters, blanks are -)
	 * @param guessed string of guessed letters
	 * @return Guess letter
	 */
	public char makeGuess(String word, String guessed) {
		
		//V1 - Achieved
		/*
		 * Takes dictionaryMap and processes only ones with correct length.
		 * Regex those words and puts matches into words List.
		 */
		if (words == null) {
			words = new ArrayList<String>();
			String regex = word.replaceAll("-", ".");
			for (String str : dictionaryMap.get(word.length())) {
				if (Pattern.matches(regex, str)) {
					words.add(str);
					for (char c: str.toCharArray()) {
						if (guessed.contains("" + c))
							continue;
						
						/*
						 * Adds a new key with value of 1
						 * OR
						 * +1 a value of an existing key
						 */
						int temp = 0;
						if (characterMap.containsKey(c))
							temp = characterMap.get(c);
						characterMap.put(c, ++temp);
					}
				}
			}
			
			//Garbage collection
			dictionaryMap = null;
		}
		
		
		//Convert characterMap from key->value to value->key
		for (Map.Entry<Character, Integer> entry : characterMap.entrySet()) {
			char key = entry.getKey();
		    int value = entry.getValue();
		    
		    if (!frequencyMap.containsKey(value))
		    	frequencyMap.put(value, new ArrayList<Character>());
		    frequencyMap.get(value).add(key);
		}
		
		//produces output map.
		System.out.println(words.toString());
		
		System.out.println(characterMap.toString());
		
		System.out.println(frequencyMap.toString());
		System.out.println(frequencyMap.get(frequencyMap.lastKey()));
		
		//Next step, count characters excluding matched, and guessed
		//Guessed should inlcude matched+missed characters
		
		return frequencyMap.get(frequencyMap.lastKey()).get(0);
	}
}
