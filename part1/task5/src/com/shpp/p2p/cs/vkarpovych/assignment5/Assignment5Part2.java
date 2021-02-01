package com.shpp.p2p.cs.vkarpovych.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    /**
     * Starting point of executing program
     */
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */

    private String addNumericStrings(String n1, String n2) {

        //reverse first line: input 123 -> output 321
        StringBuilder stringBuilder = new StringBuilder(n1);
        n1 = (String.valueOf(stringBuilder.reverse()));

        //reverse second line: input 100 -> output 001
        stringBuilder = new StringBuilder(n2);
        n2 = (String.valueOf(stringBuilder.reverse()));

        //aligning strings by length: input: n1 = 1; n2 = 99 -> output: n1 = 10; n2 = 99
        StringBuilder n1Builder = new StringBuilder(n1);
        while (n1Builder.length() < n2.length()) {
            n1Builder.append("0");
        }
        n1 = n1Builder.toString();

        //aligning strings by length: input: n1 = 12345; n2 = 23 -> output: n1 = 12345; n2 = 23000
        StringBuilder n2Builder = new StringBuilder(n2);
        while (n2Builder.length() < n1.length()) {
            n2Builder.append("0");
        }
        n2 = n2Builder.toString();

        //invoke method -> calculate sum of two numbers -> return result of calculations
        return calculateSum(n1, n2);
    }

    /**
     * method that takes two parameters of type string, adds them and returns the result of calculations
     *
     * @param firstString  - The first number.
     * @param secondString - The second number.
     * @return - A String representation of n1 + n2
     */
    private String calculateSum(String firstString, String secondString) {
        //variables that stores the rest (excess) and the sum of the two chars of each line (sumPairOfChars)
        int excess = 0, sumPairOfChars;
        //variables that stores every char of string step by step
        char getCharFromFirstString, getCharFromSecondString;
        //resulting string
        String result = "";

        for (int j = 0; j < firstString.length(); j++) {
            //get every char of first string
            getCharFromFirstString = firstString.charAt(j);
            //get every char of second string
            getCharFromSecondString = secondString.charAt(j);

            /*add the value of the char from the first string and the second
              transform result into int type and to the sum add rest */
            sumPairOfChars = Integer.parseInt(
                    String.valueOf((getCharFromFirstString - '0')
                                     + (getCharFromSecondString - '0'))
            );

            if (excess == 1) {
                sumPairOfChars += excess;
                excess = 0;
            }

            if (sumPairOfChars > 9) {
                if (firstString.length() == 1 || secondString.length() == 1) {
                    result += sumPairOfChars;
                } else {
                    int temp = sumPairOfChars - 10;
                    //increment rest
                    excess = 1;
                    result += String.valueOf(temp);
                }
            } else {
                int sumFinal = sumPairOfChars + excess;
                excess = 0;
                if (sumFinal > 9) {
                    excess = 1;
                    sumFinal -= 10;
                }
                result += String.valueOf(sumFinal);
            }
        }
        if (excess == 1) {
            result += String.valueOf(1);
        }

        //transform result of calculation into common view by reversing string: result = 021 -> 120
        if (firstString.length() != 1 || secondString.length() != 1) {
            StringBuilder stringBuilder1 = new StringBuilder(result);
            result = String.valueOf(stringBuilder1.reverse());
        }

        //return result of calculations in format of string
        return result;
    }
}



