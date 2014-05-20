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

import java.util.regex.Pattern;

public class Main {
	
	public Main() {
		
	}
	
	public void regexTest(String working, String word) {
		String regex = working.replaceAll("-", ".");
		System.out.println(regex);
		
		boolean match = Pattern.matches(regex, word);
		System.out.println(match);
	}
	
	public void runner() {
		//MATCH
		regexTest("a-c-e-", "abcdef");
		//NO MATCH
		regexTest("a-c-e-", "abcde");
		//NO MATCH
		regexTest("a-c-e-", "bbcdef");
	}
	
	public static void main(String[] args) {
//		new Main().runner();
		
		
		
//		MyHangmanAI test = new MyHangmanAI();
//		System.out.println(test.makeGuess("----", ""));
//		System.out.println(test.makeGuess("----", "e"));
//		System.out.println(test.makeGuess("aa--", "ea"));
//		System.out.println(test.makeGuess("aals", "eas"));
		
		double accuracy = new MyGuesser(AI.PLAUE, true).guess("aals");
//		double accuracy = new MyGuesser(AI.MINE, false).guessAll();
		System.out.println(accuracy);
	}
}
