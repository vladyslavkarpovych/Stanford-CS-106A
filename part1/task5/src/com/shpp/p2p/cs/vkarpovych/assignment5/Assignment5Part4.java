package com.shpp.p2p.cs.vkarpovych.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part4 extends TextProgram {

    //list which stores content (lines) of csv file
    private List<String> contentOfCSVFile = new ArrayList<>();

    @Override
    public void run() {
        println(extractColumn("test.csv", 1));
    }

    /**
     * method, which read csv file and add content to list
     */
    private void readFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            try {
                //construct to read a string
                BufferedReader reader = new BufferedReader(new FileReader(file));
                //variable that takes line from file
                String line;
                while ((line = reader.readLine()) != null) {
                    contentOfCSVFile.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw null;
        }
    }

    /**
     * remove extra characters from the word
     *
     * @return modified string
     */
    private String replaceCharacters(StringBuilder stringBuilder) {
        return stringBuilder.toString()
                .replaceAll("^\"|\"$", "")
                .replaceAll(',' + "$", "")
                .replace("\"\"", "\"")
                .trim();
    }

    /**
     * method that extract column from csv file
     *
     * @param filename    - name of file
     * @param columnIndex - index of column
     * @return ArrayList which contains information from the column
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        readFile(filename);
        ArrayList<String> listWithSuitableWords = new ArrayList<>();
        int i = 0;
        do {
            String stringFromFile = contentOfCSVFile.get(i);
            listWithSuitableWords.add(fieldsIn(stringFromFile).get(columnIndex));
            i++;
        } while (i < contentOfCSVFile.size());
        return listWithSuitableWords;
    }

    /** method that takes line from file and return all fields from it
     * @param line - row of text
     * @return all fields from line
     */
    private ArrayList<String> fieldsIn(String line) {
        //list which stores field of line
        ArrayList<String> fieldOfRow = new ArrayList<>();
        //construct which allows to modify string
        StringBuilder fieldBuilder = new StringBuilder();

        boolean quotes = false;

        int j = 0;
        while (j < line.length()) {
            char currentChar = line.charAt(j);
            fieldBuilder.append(currentChar);

            switch (currentChar) {
                case '"':
                    quotes = !quotes;
                    break;
            }

            if (j == line.length() - 1 || (!quotes && currentChar == ',')) {
                fieldOfRow.add(replaceCharacters(fieldBuilder));
                fieldBuilder = new StringBuilder();
            } else if (currentChar == ',' && j == line.length() - 1) {
                fieldOfRow.add("");
            }
            j++;
        }
        return fieldOfRow;
    }
}

