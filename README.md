#Scrabble solver service by Mrinabh Dutta (mrinabh.dutta@gmail.com)

#####Functional Requirement
HTTP REST service that returns Scrabble words for a given set of letters


#####Input(s):
* Set of letters
* Dictionary: words.txt (from https://github.com/dwyl/english-words/blob/master/words.txt)
* Scrabble score calculation:

```
    Points | Letters
    -------+-----------------------------
       1   | A, E, I, L, N, O, R, S, T, U
       2   | D, G
       3   | B, C, M, P
       4   | F, H, V, W, Y
       5   | K
       8   | J, X
      10   | Q, Z
```
#####Output:
* JSON list of strings sorted by Scrabble score
* If no match then return empty JSON list



#####IMPLEMENTATION DETAILS:
My application emulates a real-word game of Scrabble: When user has a set of tiles (letters), he/she determines if a word can be spelled by "using" the tiles or letters. When a tile is placed or used, it is no longer available for another alphabet therefore, gets removed from the available letters. The business logic is encapsulated in ScrabbleService class, the algorithm is as follows:

1. Iterate through the words in dictionary (populated from http://recruiting.bluenile.com/words.txt)
2. For each word in dictionary:
    a. Iterate through all required alphabets in word
    b. Check if the alphabet is available in the input (letters):
    		i. If found then use it to create wordResult
	c. If wordResult == word then we have found all required alphabets i.e. it is a match: Put word in resultSet
	
Additionally, I am using a custom Comparator for the sorted set resultset to sort the words per Scrabble score: the business logic can be found in ScrabbleScoreComparator class

Unit tests are in ScrabbleSolverServiceApplicationTests class - the test case names are self-explanatory
