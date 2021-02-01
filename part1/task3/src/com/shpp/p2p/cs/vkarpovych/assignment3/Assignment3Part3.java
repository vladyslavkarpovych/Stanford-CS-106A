package com.shpp.p2p.cs.vkarpovych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Implements a method (raiseToPower(double base, int exponent)) that takes
 * two parameters as input and brings the first value to the power of the second
 */

public class Assignment3Part3 extends TextProgram {

    @Override
    public void run() {
        print(raiseToPower(readBaseOfNumber(), readExponentOfNumber()));
    }

    /** a method that reads the base of a number from the keyboard and return it */
    private double readBaseOfNumber() {
        print("Enter base of number: ");
        return readDouble();
    }

    /** a method that reads the exponent of a number from the keyboard and return it */
    private int readExponentOfNumber() {
        print("Enter exponent of number: ");
        return readInt();
    }

    /** Method for raising the first number to the power of the second
     *  @param base     - raise this number to the power of second number (exponent)
     *  @param exponent - a number that indicates to what degree to raise first number (base) */
    private double raiseToPower(double base, int exponent) {
        //a variable that stores the result of calculations
        double temp = 1;
        if (exponent == 0) {
            return 1;
        } else if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                //for example, base equals 2 and exponent equals 5:
                //temp = 1 * 2 * 2 * 2 * 2 * 2
                temp = temp * base;
            }
            //return result of calculations
            return temp;
        } else {
            //for example exponent equals -2, then -(-2) = 2;
        for (int i = 0; i < -exponent; i++) {
            temp = temp * base;
        }
            return 1 / temp;
        }
    }
}
