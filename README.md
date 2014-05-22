A Better Strategy for Hangman
==========

Attempting to beat Dr. Chris Plaue PhD's AI for hangman

The methods available in HangmanAI include
- default constructor whose responsibilities are to open up dictionary.data (which must be in the same directory as your hangman program)
- char makeGuess (String, String) where the first parameter is the word with blanks (lower case letters, blanks are -). The second parameter is a string of guessed words. 

##AI Step by Step
**Word:** interaction

####Step 0
Constructor:
* Load Dictionary into a Map where the key is Integer, and the value is a ArrayList of Strings
* The key is the length of the word, and the value is the words which are that length

```
2=[aa, ab]
3=[aah, aal, aas, aba]
4=[aahs, aals]
5=[aahed, aalii, aargh, abaca]
6=[aahing, aaliis, aarrgh, abacas]
7=[aarrghh]
8=[aardvark, aardwolf, aasvogel]
9=[aardvarks, aasvogels]
10=[aardwolves]
```

**Active:** -----------

####Step 1
First run of the method `makeGuess(String word, String guessed)` will
* deep-copy each word that is 11 characters long
* count the occurances of each characters in all the words that are 11 characters long
* make the Dictionary May in step 0 eligible for JAVA garbage collection

wordList: `[abandonment, abbreviated, abbreviates, abbreviator, abdications, abdominally, abecedarian, aberrancies, aberrations, abhorrences, abhorrently, ...`

characterMap: `{f=1866, g=4652, d=5221, e=19341, b=2758, c=7518, a=12541, n=12736, o=11360, l=8730, m=4907, j=178, k=977, h=3846, i=16729, w=867, ...`

##Validation Procedure
> accuracy = number of guesses / number of actual guesses

> MINE: 0.7792318457931986  
> PLAUE: 0.47667409819988715


I have achieved a **30.3%** increase in accuracy over Dr. Chris Plaue PhD's Algorithm.  
The Dictionary contains **172820** elements.  
Also runtime, not theoretical runtime, is a lot lower.

![runtime](https://raw.githubusercontent.com/vincentclee/hangman_ai/master/images/panning.jpg)

##Tasks
- [x] Build AI
- [x] Build Testing program
- [x] Build Validation program
- [ ] Finish/Enhance AI

# Lets get start.
