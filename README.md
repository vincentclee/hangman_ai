A Better Strategy for Hangman
==========

Attempting to beat Dr. Chris Plaue PhD's AI for hangman

The methods available in HangmanAI include
- default constructor whose responsibilities are to open up dictionary.data (which must be in the same directory as your hangman program)
- char makeGuess (String, String) where the first parameter is the word with blanks (lower case letters, blanks are -). The second parameter is a string of guessed words. 

##AI Step by Step
**Word:** interaction

###Step 0
Constructor:
* Load Dictionary into a Map where the key is Integer, and the value is a ArrayList of Strings into **dictionaryMap**
* The key is the length of the word, and the value is the words which are that length

**dictionaryMap:**  
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

###Step 1
First run of the method `makeGuess(String word, String guessed)` loop will
* deep-copy each word that is 11 characters long int **wordList**
* count the occurances of each characters in all the words that are 11 characters long into **characterMap**

**wordList:** `[abandonment, abbreviated, abbreviates, abbreviator, abdications, abdominally, abecedarian, aberrancies, aberrations, abhorrences, abhorrently, ...`  
**characterMap:** `{f=1866, g=4652, d=5221, e=19341, b=2758, c=7518, a=12541, n=12736, o=11360, l=8730, m=4907, j=178, k=977, h=3846, i=16729, w=867, ...`

###Step 2
Make the **dictionaryMap** in step 0 eligible for JAVA garbage collection

###Step 3
Convert **characterMap** from key->value to value->key into SortedMap **frequencyMap**  
**frequencyMap:** `{178=[j], 275=[q], 464=[x], 801=[z], 867=[w], 977=[k], 1707=[v], 1866=[f], 2644=[y], 2758=[b], 3846=[h], 4652=[g], 4907=[m], 5093=[p], ...`

###Step 4
Grab the last key's value off of **frequencyMap**
`[e]`

**Guess:** e
**Active:** ---e-------



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
