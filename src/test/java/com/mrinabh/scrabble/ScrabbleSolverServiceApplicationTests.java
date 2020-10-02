package com.mrinabh.scrabble;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScrabbleSolverServiceApplicationTests {

    @Autowired
    private ScrabbleController scrabbleController;

    @Autowired
    private ScrabbleService scrabbleService;

    @Test
    void contextLoads() throws Exception {
        assertThat(scrabbleController).isNotNull();
    }

    @Test
    void testScrabbleServiceStartup() {
        assertThat(scrabbleService).isNotNull();
    }

    @Test
    void testScrabbleServiceReturnsResult() {
        SortedSet<String> words = scrabbleService.getWords("hat");
        assertThat(words).isNotEmpty();
    }

    @Test
    void testScrabbleServiceReturnsSortedResult() {
        SortedSet<String> words = scrabbleService.getWords("hat");
        assertThat(words).isNotEmpty();

        SortedSet<String> expectedResponse = new TreeSet<String>(new ScrabbleScoreComparator());
        expectedResponse.add("hat");
        expectedResponse.add("ah");
        expectedResponse.add("th");
        expectedResponse.add("at");
        expectedResponse.add("a");

        assertThat(words).isEqualTo(expectedResponse);
    }

    @Test
    void testScrabbleServiceReturnsSameResultForUpperLowerCase() {
        SortedSet<String> wordsLower = scrabbleService.getWords("hat");
        SortedSet<String> wordsUpper = scrabbleService.getWords("HAT");
        SortedSet<String> wordsMixed = scrabbleService.getWords("hAt");

        assertThat(wordsLower).isNotEmpty();
        assertThat(wordsUpper).isNotEmpty();
        assertThat(wordsMixed).isNotEmpty();

        assertThat(wordsLower).isEqualTo(wordsUpper);
        assertThat(wordsLower).isEqualTo(wordsMixed);
    }

    @Test
    void testScrabbleServiceReturnsEmptyResponse() {
        SortedSet<String> emptyResponse = scrabbleService.getWords("zzz");
        assertThat(emptyResponse).isEmpty();
    }
}
