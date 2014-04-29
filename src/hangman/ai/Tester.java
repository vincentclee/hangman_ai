package hangman.ai;

/**
 * Hangman AI Tester
 * 
 * @author Vincent Lee
 * @since April 18, 2014
 * @version 1.0
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Tester {
	@Test
	public void testII() {
		new HangmanAII();
	}
	
//	@Test
	public void testIII() {
		new HangmanAIII();
	}
}
