package hangman.decompiled;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HangmanAI {
	private String filename = "dictionary.data";
	private ArrayList<Character> invalid_letters = new ArrayList<Character>();
	private ArrayList<String> dictionary = new ArrayList<String>(180000);
	private ArrayList<String> possible_words = new ArrayList<String>();

	public HangmanAI() {
		System.out.print("Running HangingCheater Constructor...initializing dictionary...");
		File localFile = null;
		Scanner localScanner = null;
		try {
			localFile = new File(this.filename);
			localScanner = new Scanner(localFile);
		} catch (FileNotFoundException localFileNotFoundException) {
			System.err.println("Error: Couldn't find " + this.filename);
			System.exit(1);
		}
		while (localScanner.hasNext()) {
			this.dictionary.add(localScanner.next());
		}
		System.out.println("..done! Dictionary contains " + this.dictionary.size() + " elements");
	}

	public char makeGuess(String paramString1, String paramString2) {
		String str1 = paramString1;

		invalid_letters = new ArrayList<Character>();
		for (int i = 0; i < paramString2.length(); i++)
			invalid_letters.add(Character.valueOf(paramString2.charAt(i)));
		for (int i = 0; i < paramString1.length(); i++)
			invalid_letters.add(Character.valueOf(paramString1.charAt(i)));
		
		for (String str2 : dictionary) {
			if (str2.length() == str1.length()) {
				char c1 = '\001';
				for (int k = 0; k < str1.length(); k++) {
					if ((str1.charAt(k) != '-') && (str2.charAt(k) != str1.charAt(k))) {
						c1 = '\000';
						break;
					}
					if ((str1.charAt(k) == '-') && (this.invalid_letters.contains(Character.valueOf(str2.charAt(k))))) {
						c1 = '\000';
						break;
					}
				}
				if (c1 != 0) {
					this.possible_words.add(str2);
				}
			}
		}
		char[] localObject1 = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

		int j = -1;
		char c1 = ' ';
		for (char c2 : localObject1) {
			int i1 = 0;
			for (String str3 : possible_words) {
				for (int i2 = 0; i2 < str3.length(); i2++) {
					if ((str1.charAt(i2) == '-') && (str3.charAt(i2) == c2)) {
						i1++;
					}
				}
			}
			boolean bool = invalid_letters.contains(Character.valueOf(c2));
			if ((i1 > j) && (!bool)) {
				j = i1;
				c1 = c2;
			}
		}
		return c1;
	}
}