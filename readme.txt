Scrabble solver service by Mrinabh Dutta (mrinabh.dutta@gmail.com)
------------------------------------------------------------------
Date: 10/01/2020
NOTE: I used Eclipse IDE with Spring STS plugin to create the project. If you are using Eclipse, then should be able to import the project as-is.

INSTRUCTIONS TO BUILD AND RUN THE SERVICE:
1. Download and unzip scrabble-solver-service.tar (tar -xvf scrabble-solver-service.tar)
2. Go to scrabble-solver-service directory
3. Run command ./mvnw spring-boot:run


FUNCTIONAL REQUIREMENT:
HTTP REST service that returns Scrabble words for a given set of letters


Input(s):
-- Set of letters
-- Dictionary
-- Scrabble score calculation:
    Points | Letters
    -------+-----------------------------
       1   | A, E, I, L, N, O, R, S, T, U
       2   | D, G
       3   | B, C, M, P
       4   | F, H, V, W, Y
       5   | K
       8   | J, X
      10   | Q, Z

Output:
-- JSON list of strings sorted by Scrabble score
-- If no match then return empty JSON list



IMPLEMENTATION DETAILS:
My application emulates a real-word game of Scrabble: When user has a set of tiles (letters), he/she determines if a word can be spelled by "using" the tiles or letters. When a tile is placed or used, it is no longer available for another alphabet therefore, gets removed from the available letters. The business logic is encapsulated in ScrabbleService class, the algorithm is as follows:

1. Iterate through the words in dictionary
2. For each word in dictionary:
    a. Iterate through all required alphabets in word
    b. Check if the alphabet is available in the input (letters):
    		i. If found then use it to create wordResult
	c. If wordResult == word then we have found all required alphabets i.e. it is a match: Put word in resultSet
	
Additionally, I am using a custom Comparator for the sorted set resultset to sort the words per Scrabble score: the business logic can be found in ScrabbleScoreComparator class

Unit tests are in ScrabbleSolverServiceApplicationTests class - the test case names are self-explanatory


ANALYSIS:
The core business logic in my solution can be broken down into the following steps:
- Iterate through array of Strings (dictionary): θ(1)
- For each word in the dictionary, search in letters (nested for loop): O(n^2)
- For every letter found, add to resultWord: θ(1)

This brings overall time complexity of O(n^2)
