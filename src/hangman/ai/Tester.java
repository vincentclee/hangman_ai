package hangman.ai;

/**
 * Hangman AI Tester
 * 
 * @author Vincent Lee
 * @since April 18, 2014
 * @version 1.0
 */

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class Tester {
	public char method2(String a, String b) {
		return 'a';
	}
	
	@Test
	public void PlaueConstructor() {
		try {
			Class[] paramString = new Class[2];	
			paramString[0] = String.class;
			paramString[1] = String.class;
			
			Class cls = Class.forName("HangmanAI");
			Object obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void MyConstructor() {
		new MyHangmanAI();
	}
	
	@Test
	public void PlaueMakeGuess() {
		try {
			Class[] paramString = new Class[2];	
			paramString[0] = String.class;
			paramString[1] = String.class;
			
			Class cls = Class.forName("HangmanAI");
			Object obj = cls.newInstance();
			
			Method method = cls.getDeclaredMethod("makeGuess", paramString);
			System.out.println(method.invoke(obj, "aa--", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void MyMakeGuess() {
		new MyHangmanAI().makeGuess("aa--", "");
	}
}
