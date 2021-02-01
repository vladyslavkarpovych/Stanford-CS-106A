package com.shpp.p2p.cs.vkarpovych.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * Write a console program that will accept the input of 3 numbers
 * of type double (a, b, c), and give the roots of the quadratic equation.
 */
public class Assignment2Part1 extends TextProgram {
    //variables for storing input values
    private double a, b, c;
    //variable for storing the values of the discriminant
    private double discriminant;

    public void run() {
        //reading three digits from keyboard
        inputFromKeyboard();
        //finding discriminant of quadratic equation
        findingDiscriminant();
        //calculating the square roots of an equation
        rootsOfEquation();
    }

    //method for reading three numbers from the keyboard
    private void inputFromKeyboard() {
        try {
            a = readDouble("Please enter a: ");
            while (a == 0) {
                a = readDouble("First value cannot equals to zero. Repeat your input: ");
            }
            b = readDouble("Please enter b: ");
            c = readDouble("Please enter c: ");
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    //equation discriminant search method
    private void findingDiscriminant() {
        discriminant = b * b - 4 * (a * (c));
    }

    //a method that evaluates the roots of an expression and output them to console
    private void rootsOfEquation() {
        double firstRoot = (-b + Math.sqrt(discriminant)) / (2 * a);
        double secondRoot = (-b - Math.sqrt(discriminant)) / (2 * a);
        if (discriminant < 0) {
            println("There are no real roots");
        } else if (discriminant == 0) {
            firstRoot = -b / (2 * a);
            println("There is one root: " + firstRoot);
        } else if (discriminant > 0) {
            println("There are two roots: " + firstRoot + " and " + secondRoot);
        }
    }
}