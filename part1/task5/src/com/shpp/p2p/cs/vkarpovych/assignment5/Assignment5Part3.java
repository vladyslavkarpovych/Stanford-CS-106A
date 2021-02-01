package com.shpp.p2p.cs.vkarpovych.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Assignment5Part3 extends TextProgram {

    //a list containing all words from the file that are longer than 2
    private List<String> fileContents = new ArrayList<>();
    //list that stores a suitable words based on entered letters
    private List<String> listWithSuitableWords = new ArrayList<>();
    //line, which stores user input
    private String userInput;

    @Override
    public void run() {
        readFile();
        userInteraction();
    }

    /** method that reads words from file and add them to list */
    private void readFile() {
        try {
            //construct to read a string
            BufferedReader bufferedReader = new BufferedReader(new FileReader("en-dictionary.txt"));
            //line, which step by step takes word from file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 2) {
                    fileContents.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            println("Something went wrong!");
        }
    }

    /** method of reading three letters from the user's keyboard and output result of program work*/
    private void userInteraction() {
        while (true) {
            userInput = readLine("Input 3 letters together of english alphabet: ");
            userInput = userInput.toLowerCase();
            outputResult();
            println();
        }
    }

    /**
     * method that compares user input and word from file.
     * if word from file contains letters in a certain sequence that the user entered,
     * then add this word to the list of suitable words
     * @param fileWord - word from file
     */
    private void findSuitableWords(String fileWord) {
        for (int j = 0; j < fileWord.length(); j++) {
            if (userInput.charAt(0) == fileWord.charAt(j)) {
                for (int n = j + 1; n < fileWord.length(); n++) {
                    if (userInput.charAt(1) == fileWord.charAt(n)) {
                        for (int p = n + 1; p < fileWord.length(); p++) {
                            if (userInput.charAt(2) == fileWord.charAt(p)) {
                                listWithSuitableWords.add(fileWord);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * go through the list of all words from the file and take suitable ones from there
     */
    private void addSuitableWordsToList() {
        for (String fileWord : fileContents) {
            findSuitableWords(fileWord);
        }
    }

    /** method that allows to display the result of the program on the screen */
    private void outputResult() {
        addSuitableWordsToList();
        if (listWithSuitableWords.isEmpty()) {
            println("It's impossible to make words from this input -> " + userInput);
        } else {
            for (String temp : listWithSuitableWords) {
                println(temp);
            }
        }
        //clear the list of all words
        listWithSuitableWords.removeAll(Collections.unmodifiableList(listWithSuitableWords));
    }
}
