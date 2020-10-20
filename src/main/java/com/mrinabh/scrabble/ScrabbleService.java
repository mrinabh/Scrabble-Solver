package com.mrinabh.scrabble;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Component encapsulating the business logic for Scrabble solver service.
 * 
 * @author mrinabh
 * @since 10/01/2020
 */
@Component
public class ScrabbleService {

    private List<String> dictionary;

    /**
     * Constructor - initialize dictionary
     */
    public ScrabbleService() {
        initializeDictionary();
    }

    /**
     * Returns words from <code>dictionary</code> that can be made with the given
     * letters. The returned set of words are sorted by their Scrabble score.
     * 
     * @param letters
     * @return List of strings sorted by Scrabble score
     */
    public SortedSet<String> getWords(String letters) {

        SortedSet<String> words = new TreeSet<String>(new ScrabbleScoreComparator());

        if (letters != null && letters.length() > 0) {
            // Iterate through all words in dictionary, add to words if able to spell using
            // letters
            for (int i = 0; i < dictionary.size(); i++) {
                String word = dictionary.get(i);
                if (word != null && word.length() > 0) {
                    if (canSpellWord(letters.toLowerCase(), word.toLowerCase())) {
                        words.add(word);
                    }
                }
            }
        }

        return words;
    }

    /**
     * Determine if we have all alphabets in <code>letters</code> to spell the
     * <code>word</code>
     * 
     * Imagine a real world Scrabble game: The player uses tiles (alphabets) to form
     * words, and as he/she does this eliminates the tiles (alphabets). This method
     * iterates through the alphabets in the word as they are found in the available
     * letters, then returns true if all required alphabets are found.
     * 
     * @param letters
     * @param word
     * @return true/false
     */
    private boolean canSpellWord(String letters, String word) {

        // if the length of the word is longer than letters then obviously you cannot
        // spell it so return false
        if (letters.length() < word.length())
            return false;

        char[] alphabetsRequired = word.toCharArray();
        char[] alphabetsInHand = letters.toCharArray();
        String wordResult = "";

        for (int i = 0; i < alphabetsRequired.length; i++) {
            for (int k = 0; k < alphabetsInHand.length; k++) {
                if (alphabetsRequired[i] == alphabetsInHand[k]) {

                    // Add found alphabet to result
                    wordResult += alphabetsInHand[k];

                    // Remove used alphabet from alphabetsInHand so we do not use it again. For
                    // sake of simplicity, we will
                    // simply mark it as non-alphabet '0' (zero)
                    alphabetsInHand[k] = '0';

                }
            }
        }

        // Compare word formed/result with desired word
        return wordResult.equals(word);
    }

    /**
     * Initialize the dictionary: Read from text file and populate
     * <code>dictionary</code> Strings list
     */
    private void initializeDictionary() {
        try {
            File textFile = new ClassPathResource(
                    "words.txt").getFile();
            Scanner scanner = new Scanner(textFile.getCanonicalFile());
            dictionary = new ArrayList<String>();
            while (scanner.hasNext()) {
                String entry = scanner.nextLine();
                if (entry != null && entry.length() > 0)
                    dictionary.add(entry);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
