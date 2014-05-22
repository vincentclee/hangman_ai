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
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
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
	private List<String> wordsList;
	
	/**
	 * Default Constructor
	 */
	public MyHangmanAI() {
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
		Map<Character, Integer> characterMap = new HashMap<Character, Integer>();
		SortedMap<Integer, List<Character>> frequencyMap = new TreeMap<Integer, List<Character>>();
		
		//Generate a missed characters string
		String missed = "";
		for (int i = 0; i < guessed.length(); i++)
			if (!word.contains("" + guessed.charAt(i)))
				missed += guessed.charAt(i);
		
		/*
		 * Regular Expression
		 * ------------------
		 * word: aa--
		 * becomes
		 * regex: aa..
		 * 
		 * Only matches words with the [a][a][any character][any character]
		 */
		String regex = word.replaceAll("-", ".");
		
		/*
		 * Takes dictionaryMap and processes only ones with correct length.
		 * Deep-Copy
		 * Words characters are counted and put into a map.
		 */
		if (wordsList == null) {
			wordsList = new ArrayList<String>();
			
			for (String str : dictionaryMap.get(word.length())) {
				wordsList.add(str);
				for (char c: str.toCharArray()) {
					//Increments existing key or adds new key
					int temp = 0;
					if (characterMap.containsKey(c))
						temp = characterMap.get(c);
					characterMap.put(c, ++temp);
				}
			}
			
			//Garbage collection
			dictionaryMap = null;
		} 
		/*
		 * The regex will only match patterns.
		 * Additional filtering for missed characters in the wordList
		 */
		else {
			Iterator<String> iter = wordsList.iterator();
			while (iter.hasNext()) {
				String str = iter.next();
				if (Pattern.matches(regex, str)) {
					//Remove word if it contains a missed character
					boolean addable = true;
					for (char c: missed.toCharArray()) {
						if (str.contains("" + c)) {
							addable = false;
							iter.remove();
							break;
						}
					}
					
					//Add word characters
					if (addable) {
						for (char c: str.toCharArray()) {
							if (guessed.contains("" + c))
								continue;
							
							//Increments existing key or adds new key
							int temp = 0;
							if (characterMap.containsKey(c))
								temp = characterMap.get(c);
							characterMap.put(c, ++temp);
						}
					}
				} 
				//Remove word if regex fails to match
				else
					iter.remove();
			}
		}
		
		//Convert characterMap from key->value to value->key
		for (Map.Entry<Character, Integer> entry : characterMap.entrySet()) {
			char key = entry.getKey();
		    int value = entry.getValue();
		    
		    if (!frequencyMap.containsKey(value))
		    	frequencyMap.put(value, new ArrayList<Character>());
		    frequencyMap.get(value).add(key);
		}
		
		//Current wordList working set
		if (DEBUG) System.out.println(wordsList.toString());
		if (DEBUG) System.out.println(characterMap.toString());
		if (DEBUG) System.out.println(frequencyMap.toString());
		if (DEBUG) System.out.println(frequencyMap.get(frequencyMap.lastKey()));
		/*
		 * TODO: Refine Algorithm
		 * Currently returns first item in hash.
		 * 
		 * Example:
		 * 2160=[n, q, z]
		 * 
		 * [n] is returned.
		 * 
		 * Would like to prioritize which highest count character gets returned.
		 */
		return frequencyMap.get(frequencyMap.lastKey()).get(0);
	}
}
