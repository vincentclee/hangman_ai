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
 * Hangman AI Validator
 * 
 * @author Vincent Lee
 * @since May 21, 2014
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validator {
	public Validator() {}
	
	public double computeAccuracy(String accuracyLog) {
		double accuracy = 0.0;
		
		//Open File and load into Scanner
		File filename = null;
		Scanner scanner = null;
		try {
			filename = new File(accuracyLog);
			scanner = new Scanner(filename);
		} catch (FileNotFoundException localFileNotFoundException) {
			System.err.println("Error: Couldn't find " + accuracyLog);
			System.exit(1);
		}
		
		int counter = 0;
		while (scanner.hasNext()) {
			scanner.next();
			accuracy += Double.parseDouble(scanner.next());
			counter++;
		}
		scanner.close();
		
		return accuracy / (double) counter;
	}
	
	public static void main(String[] args) {
		double accuracy = new Validator().computeAccuracy("logs/accuracy_MINE.log");
		System.out.println("MINE: " + accuracy);
		
		accuracy = new Validator().computeAccuracy("logs/accuracy_PLAUE.log");
		System.out.println("PLAUE: " + accuracy);
	}
}
