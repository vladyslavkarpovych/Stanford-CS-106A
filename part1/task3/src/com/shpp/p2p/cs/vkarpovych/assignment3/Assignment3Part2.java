package com.shpp.p2p.cs.vkarpovych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Read the number from the keyboard
 * and perform actions with a number until it equals one.
 */
public class Assignment3Part2 extends TextProgram {

    //a variable that stores the value entered from the keyboard
    private static int valueFromKeyboard;

    @Override
    public void run() {
        readingNumber();
        findingNumberOne();
    }

    /**
     * Method for reading a number from keyboard until it equals more than 0
     */
    private void readingNumber() {
        do {
            print("Enter a number: ");
            valueFromKeyboard = readInt();
        } while (valueFromKeyboard <= 0);
    }

    /**
     * Method for finding number one.
     * The calculation is carried out until the number becomes equal to one.
     */
    private void findingNumberOne() {
        do {
            //temporary variable for storing a value, which input from keyboard
            int temp;
            if(valueFromKeyboard == 1) {
                break;
            } else if (valueFromKeyboard % 2 == 1) {
                temp = valueFromKeyboard;
                valueFromKeyboard = (valueFromKeyboard * 3) + 1;
                println(temp + " is odd so I make 3n + 1: " + valueFromKeyboard);
            } else if(valueFromKeyboard % 2 == 0){
                temp = valueFromKeyboard;
                valueFromKeyboard /= 2;
                println(temp + " is even so I take half: " + valueFromKeyboard);
            }
        } while (valueFromKeyboard != 1);
        println("end");
    }
}
