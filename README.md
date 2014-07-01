A Better Strategy for Hangman
==========

Attempting to beat Dr. Chris Plaue PhD's AI for hangman

The methods available in HangmanAI include
- default constructor whose responsibilities are to open up dictionary.data (which must be in the same directory as your hangman program)
- char makeGuess (String, String) where the first parameter is the word with blanks (lower case letters, blanks are -). The second parameter is a string of guessed words. 

##Tasks
- [x] Build AI
- [x] Build Testing program
- [x] Build Validation program
- [ ] Finish/Enhance AI (priority map for characters)
- [ ] Speed up Runtime
- [ ] Use JAVA 8's MapReduce .parallelStream().filter(predicate)!

##Validation Procedure
> accuracy = number of guesses / number of actual guesses

> MINE: 0.7792318457931986  
> PLAUE: 0.47667409819988715


I have achieved a **30.3%** increase in accuracy over Dr. Chris Plaue PhD's Algorithm.  
The Dictionary contains **172820** elements.  
Also runtime, not theoretical runtime, is a lot lower.

![runtime](https://raw.githubusercontent.com/vincentclee/hangman_ai/master/images/panning.jpg)

##AI Step by Step
**Word:** interaction

###Step 0
--
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

**Word:** -----------

###Step 1
--
First run of the method `makeGuess(String word, String guessed)` loop will
* deep-copy each word that is 11 characters long int **wordList**
* count the occurances of each characters in all the words that are 11 characters long into **characterMap**

**wordList:** `[abandonment, abbreviated, abbreviates, abbreviator, abdications, abdominally, abecedarian, aberrancies, aberrations, abhorrences, abhorrently, ...`  
**characterMap:** `{f=1866, g=4652, d=5221, e=19341, b=2758, c=7518, a=12541, n=12736, o=11360, l=8730, m=4907, j=178, k=977, h=3846, i=16729, w=867, ...`

**Word:** -----------

###Step 2
--
Make the **dictionaryMap** in step 0 eligible for JAVA garbage collection

**Word:** -----------

###Step 3
--
Convert **characterMap** from key->value to value->key into SortedMap **frequencyMap**  
**frequencyMap:** `{178=[j], 275=[q], 464=[x], 801=[z], 867=[w], 977=[k], 1707=[v], 1866=[f], 2644=[y], 2758=[b], 3846=[h], 4652=[g], 4907=[m], 5093=[p], ...`

**Word:** -----------

###Step 4
--
Grab the last key's value off of **frequencyMap** `[e]`

**Guess:** e  
**Word:** ---e-------

###Step 5
--
Second run of `makeGuess("---e-------", "e")`  
Convert the `---e-------` into `...e.......` for the `Pattern.matches(regex, str)`  
Run the Pattern matcher over each word in **wordList**  
The regex will only match words which are the same length, and contain all the characters in the same place, and all '.' are ignored, words which don't match are removed.  
Also see if the word in **wordList** contains any of the missed characters, (guessed - active) and remove word.  
Convert **characterMap** into **frequencyMap**  

**wordList:** `[abnegations, abreactions, absenteeism, accelerando, accelerants, accelerated, accelerates, accelerator, accentually, accentuated, accentuates, acceptances, acceptation, acceptingly, ...`  
**characterMap:** `{f=294, g=508, d=737, b=273, c=756, a=1257, n=1664, o=895, l=978, m=481, j=19, k=122, h=396, i=1794, w=141, v=263, u=540, t=1561, s=1738, ...`  
**frequencyMap:** `{11=[q], 19=[j], 64=[z], 66=[x], 122=[k], 141=[w], 263=[v], 273=[b], 294=[f], 333=[y], 396=[h], 481=[m], 508=[g], 540=[u], 586=[p], 737=[d], 756=[c], 895=[o], ...`

Last key's value off of **frequencyMap** `[r]`  
If there are two characters with same frequency, it takes the first. (this area can be refined by creating a character priority list and tweaking)

**Guess:** r  
**Word:** ---er------

###Step 6
--
Third run of `makeGuess("---er------", "er")`  
**wordList:** `[adverbially, adversarial, adversaries, adversative, adverseness, adversities, advertences, advertently, advertisers, advertising, advertizing, advertorial, ...`  
**characterMap:** `{f=90, g=141, d=276, b=84, c=215, a=438, n=598, o=288, l=293, m=138, j=3, k=28, h=146, i=669, w=53, v=99, u=272, t=590, s=609, q=1, p=288, z=32, y=120, x=26}`  
**frequencyMap:** `{1=[q], 3=[j], 26=[x], 28=[k], 32=[z], 53=[w], 84=[b], 90=[f], 99=[v], 120=[y], 138=[m], 141=[g], 146=[h], 215=[c], 272=[u], 276=[d], 288=[o, p], 293=[l], 438=[a], ...`  
Last key's value off of **frequencyMap** `[i]`  

**Guess:** i  
**Word:** i--er---i--

###Step 7
--
Fourth run of `makeGuess("i--er---i--", "eri")`  
**wordList:** `[imperialism, imperialist, imperilling, inferential, inferiority, infertility, innerspring, innervating, innervation, interacting, interaction, interactive, ...`  
**characterMap:** `{f=12, g=17, d=3, c=11, a=22, n=73, o=10, l=19, m=7, h=3, v=4, u=2, t=46, s=14, p=8, z=2, y=6, x=1}`  
**frequencyMap:** `{1=[x], 2=[u, z], 3=[d, h], 4=[v], 6=[y], 7=[m], 8=[p], 10=[o], 11=[c], 12=[f], 14=[s], 17=[g], 19=[l], 22=[a], 46=[t], 73=[n]}`  
Last key's value off of **frequencyMap** `[n]`  

**Guess:** n  
**Word:** in-er---i-n

###Step 8
--
Fifth run of `makeGuess("in-er---i-n", "erin")`  
**wordList:** `[innervation, interaction, interfusion]`  
**characterMap:** `{f=1, v=1, u=1, t=4, s=1, c=1, a=2, o=3}`  
**frequencyMap:** `{1=[f, v, u, s, c], 2=[a], 3=[o], 4=[t]}`  
Last key's value off of **frequencyMap** `[t]`  

**Guess:** t  
**Word:** inter--ti-n

###Step 9
--
Sixth run of `makeGuess("inter--ti-n", "erint")`  
**wordList:** `[interaction]`  
**characterMap:** `{c=1, a=1, o=1}`  
**frequencyMap:** `{1=[c, a, o]}`  
Last key's value off of **frequencyMap** `[c, a, o]`  

**Guess:** c  
**Word:** inter-cti-n

###Step 10
--
Sixth run of `makeGuess("inter-cti-n", "erintc")`  
**wordList:** `[interaction]`  
**characterMap:** `{a=1, o=1}`  
**frequencyMap:** `{1=[a, o]}`  
Last key's value off of **frequencyMap** `[a, o]`  

**Guess:** a  
**Word:** interacti-n

###Step 11
--
Sixth run of `makeGuess("interacti-n", "erintca")`  
**wordList:** `[interaction]`  
**characterMap:** `{o=1}`  
**frequencyMap:** `{1=[o]}`  
Last key's value off of **frequencyMap** `[o]`  

**Guess:** o  
**Word:** interaction

**Accuracy:** 1.0

# Lets get start.
