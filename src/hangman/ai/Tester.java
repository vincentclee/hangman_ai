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
 * Hangman AI Tester
 * 
 * @author Vincent Lee
 * @since April 18, 2014
 * @version 1.0
 */

import org.junit.Test;

public class Tester {
	@SuppressWarnings({ "rawtypes", "unused" })
	@Test
	public void PlaueConstructor() {
		try {
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
		new MyGuesser(AI.PLAUE, false).guess("interaction");
	}
	
	@Test
	public void MyMakeGuess() {
		new MyGuesser(AI.MINE, false).guess("interaction");
	}
	
	@Test
	public void PlaueGuessAll() {
		System.out.println(new MyGuesser(AI.PLAUE, false).guessAll());
	}
	
	@Test
	public void MyGuessAll() {
		System.out.println(new MyGuesser(AI.MINE, false).guessAll());
	}
}
