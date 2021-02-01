package com.shpp.p2p.cs.vkarpovych.assignment5;


import com.shpp.cs.a.console.TextProgram;

import java.util.Arrays;
import java.util.List;

public class Assignment5Part1 extends TextProgram {

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word. */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {

        //variable that stores count of syllables in word
        int countOfSyllables = 0;

        //list that stores vowels characters
        List<Character> listOfVowels = Arrays.asList('e', 'y', 'a', 'i', 'o', 'u');

        for (int j = 0; j < word.length(); j++) {
            char currentChar = word.toLowerCase().charAt(j);
            if (listOfVowels.contains(currentChar)) {
                countOfSyllables++;
                if ((j == word.length() - 1 && currentChar == 'e') || (j > 0 &&
                        listOfVowels.contains(word.toLowerCase().charAt(j - 1)))) {
                    countOfSyllables--;
                }
            }
        }

        if (countOfSyllables > 0) {
            return countOfSyllables;
        } else {
            return 1;
        }
    }
}