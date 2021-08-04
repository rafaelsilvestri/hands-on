package com.github.rafaelsilvestri.subsequence;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Given two strings X and Y. The task is to find the length of the longest subsequence
 * of string X which is a substring in sequence Y.
 */
public class SubsequenceTest {

    @Test
    void shouldReturnAllSubsequencesOfAbcd() {
        // arrange
        String str = "abcd";
        List<String> expected = Arrays.asList("abcd", "abc", "abd", "ab", "acd",
                "ac", "ad", "a", "bcd", "bc", "bd", "b", "cd", "c", "d", "");

        // act
        SequenceGenerator generator = new SequenceGenerator();
        List<String> result = generator.findSequences(str);

        // assert
        System.out.println(result);
        assertTrue(result.size() == expected.size() && result.containsAll(expected));
    }

    @Test
    void longestSequenceShouldBeThree() {
        // arrange
        String str1 = "abcd";
        String str2 = "abc";

        // act
        SequenceGenerator generator = new SequenceGenerator();
        int result = generator.longest(str1, str2);

        // assert
        assertEquals(3, result);
    }

    @Test
    void longestSequenceShouldBeSeven() {
        // arrange
        String str1 = "hackers";
        String str2 = "hackerranks";

        // act
        SequenceGenerator generator = new SequenceGenerator();
        int result = generator.longest(str1, str2);

        // assert
        assertEquals(7, result);
    }

}
