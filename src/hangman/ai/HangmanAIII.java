package hangman.ai;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class HangmanAIII {
	
	private String filename = "dictionary.data";
	private ArrayList<String> dictionary = new ArrayList(180000);
	
	public HangmanAIII() {
		File localFile = null;
		Scanner localScanner = null;
		
		try {
			localFile = new File(filename);
			localScanner = new Scanner(localFile);
		} catch (FileNotFoundException localFileNotFoundException) {
			System.err.println("Error: Couldn't find " + filename);
			System.exit(1);
		}
		
		while (localScanner.hasNext()) {
			String str = localScanner.next();
//			System.out.println(str);
			dictionary.add(str);
		}

		System.out.println(dictionary.size() + " elements");
	}
}
