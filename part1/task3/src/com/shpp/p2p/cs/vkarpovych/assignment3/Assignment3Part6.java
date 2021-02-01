package com.shpp.p2p.cs.vkarpovych.assignment3;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Date;

 /** A program that creates an animation with a duration of 5 seconds */
public class Assignment3Part6 extends WindowProgram {

     //Optimal values of the application window. Values can only be changed to values that are higher than optimal.
     public static final int APPLICATION_WIDTH = 1200;
     public static final int APPLICATION_HEIGHT = 800;

    //total time of pause between frames
    private static final double PAUSE_TIME = 1000.0 / 48;

    //variables that stores width and height the base of letter
    private static final double WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER = 400;
    private static final double HEIGHT_OF_HORIZONTAL_RECTANGLE_OF_LETTER = 30;

    //variables that stores width and height of vertical parts of letter
    private static final double WIDTH_OF_VERTICAL_RECTANGLE_OF_LETTER = 30;
    private static final double HEIGHT_OF_VERTICAL_RECTANGLE_OF_LETTER = 300;

    //variables that stores sizes of sign
    private static final double WIDTH_OF_VERTICAL_PARTS_OF_SIGN = 30;
    private static final double HEIGHT_OF_VERTICAL_PARTS_OF_SIGN = 150;
    private static final double WIDTH_OF_HORIZONTAL_PARTS_OF_SIGN = 150;
    private static final double HEIGHT_OF_HORIZONTAL_PARTS_OF_SIGN = 30;

    //a variable that stores the value by which to shift the base of the letter to the left of the centre
    private final double SHIFT_THE_BASE_OF_THE_LETTER_TO_LEFT = WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER / 3.0;

    //a variable that stores the value by which to shift the base of the letter below of the centre
    private final double SHIFT_THE_BASE_OF_THE_LETTER_BELOW_THE_CENTRE = HEIGHT_OF_VERTICAL_RECTANGLE_OF_LETTER / 2.0;

    //variables that stores position of vertical parts of letter relative to the letter base
    private static final double POSITION_OF_FIRST_RECTANGLE = 0;
    private static final double POSITION_OF_SECOND_RECTANGLE = POSITION_OF_FIRST_RECTANGLE + (WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER / 2.0) - WIDTH_OF_VERTICAL_RECTANGLE_OF_LETTER / 2;
    private static final double POSITION_OF_THIRD_RECTANGLE = POSITION_OF_FIRST_RECTANGLE + WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER - WIDTH_OF_VERTICAL_RECTANGLE_OF_LETTER;

    //variables that stores indent from right border of letter to next sign
    private static final double INDENT_FROM_LETTER_TO_FIRST_SIGN = POSITION_OF_THIRD_RECTANGLE + 150;
    private static final double INDENT_FROM_LETTER_TO_SECOND_SIGN = POSITION_OF_THIRD_RECTANGLE + 350;

    //variable that generate colors of figure
    private RandomGenerator randomGenerator;

    //the duration of the program in milliseconds
    private int fiveSeconds = 5000;

    //the running time of our program (5 seconds)
    private long programRunningTime = System.currentTimeMillis() + fiveSeconds;

     public void run() {
        //while the running time of our program is less than 5 seconds, we repeat the steps
        while (System.currentTimeMillis() < programRunningTime) {
            flagOfCountry(0, Color.BLUE);
            flagOfCountry(getHeight() / 2.0, Color.YELLOW);
            drawLogo();
            createGarland();
            tryCatch();
        }
    }

     /** a method that pauses the execution of the program and throws an exception if something goes wrong */
    private void tryCatch() {
        try {
            Thread.sleep((long) PAUSE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Method that drawing flag of country
     *  @param coordinateY - the height of the location of one of the rectangle that the flag consists of
     *  @param color       - color of one rectangle */
    private void flagOfCountry(double coordinateY, Color color) {
        GRect gRect = new GRect(0, coordinateY, getWidth(), getHeight() / 2.0);
        gRect.setFilled(true);
        gRect.setFillColor(color);
        add(gRect);
    }

    /** method that drawing logo of school */
    private void drawLogo() {
        createHorizontalPartOfLetter(0);
        createVerticalPartOfLetter(POSITION_OF_FIRST_RECTANGLE);
        createVerticalPartOfLetter(POSITION_OF_SECOND_RECTANGLE);
        createVerticalPartOfLetter(POSITION_OF_THIRD_RECTANGLE);
        createArithmeticSign(INDENT_FROM_LETTER_TO_FIRST_SIGN);
        createArithmeticSign(INDENT_FROM_LETTER_TO_SECOND_SIGN);
    }

    /** a method that creates the arithmetic sign (plus) */
    private void createArithmeticSign(double position) {
        final double SHIFT_THE_CHARACTER_CLOSER_TO_LETTER = CENTER_OF_APPLICATION_WINDOW_BY_WIDTH() + position - SHIFT_THE_BASE_OF_THE_LETTER_TO_LEFT;
        randomGenerator = RandomGenerator.getInstance();

        GRect verticalPartOfSign = new GRect(SHIFT_THE_CHARACTER_CLOSER_TO_LETTER,
                                        CENTER_OF_APPLICATION_WINDOW_BY_HEIGHT() - WIDTH_OF_VERTICAL_PARTS_OF_SIGN / 2.5,
                                            WIDTH_OF_VERTICAL_PARTS_OF_SIGN,
                                            HEIGHT_OF_VERTICAL_PARTS_OF_SIGN);
        paintingAndAddingObject(verticalPartOfSign, randomGenerator.nextColor());

        GRect horizontalPartOfSign = new GRect(SHIFT_THE_CHARACTER_CLOSER_TO_LETTER - WIDTH_OF_HORIZONTAL_PARTS_OF_SIGN / 2 + HEIGHT_OF_HORIZONTAL_PARTS_OF_SIGN / 2,
                                    CENTER_OF_APPLICATION_WINDOW_BY_HEIGHT() - HEIGHT_OF_HORIZONTAL_PARTS_OF_SIGN / 2 + HEIGHT_OF_VERTICAL_PARTS_OF_SIGN / 2 - WIDTH_OF_VERTICAL_PARTS_OF_SIGN / 2,
                                        WIDTH_OF_HORIZONTAL_PARTS_OF_SIGN,
                                        HEIGHT_OF_HORIZONTAL_PARTS_OF_SIGN);
        paintingAndAddingObject(horizontalPartOfSign, randomGenerator.nextColor());
    }

    /** method that creates a vertical part of letter
     *  @param position - a variable that takes the coordinate of the vertical part of the letter in the application window */
    private void createVerticalPartOfLetter(double position) {
        GRect gRect = new GRect(CENTER_OF_APPLICATION_WINDOW_BY_WIDTH() + position - SHIFT_THE_BASE_OF_THE_LETTER_TO_LEFT,
                               CENTER_OF_APPLICATION_WINDOW_BY_HEIGHT() - HEIGHT_OF_VERTICAL_RECTANGLE_OF_LETTER + SHIFT_THE_BASE_OF_THE_LETTER_BELOW_THE_CENTRE,
                                   WIDTH_OF_VERTICAL_RECTANGLE_OF_LETTER,
                                   HEIGHT_OF_VERTICAL_RECTANGLE_OF_LETTER);
        paintingAndAddingObject(gRect, Color.BLACK);
    }

    /** method that creates a base of letter (horizontal part of letter)
     *  @param position - a variable that takes the coordinate of the base of the letter in the application window */
    private void createHorizontalPartOfLetter(double position) {
        GRect gRect = new GRect(CENTER_OF_APPLICATION_WINDOW_BY_WIDTH() + position - SHIFT_THE_BASE_OF_THE_LETTER_TO_LEFT,
                               CENTER_OF_APPLICATION_WINDOW_BY_HEIGHT() + SHIFT_THE_BASE_OF_THE_LETTER_BELOW_THE_CENTRE,
                                   WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER,
                                   HEIGHT_OF_HORIZONTAL_RECTANGLE_OF_LETTER);
        paintingAndAddingObject(gRect, Color.BLACK);
    }

    /** a method that creates rectangles around the entire border of the application window */
    private void createGarland() {
        for (int i = 0; i < getWidth() - 30 || i < getHeight() - 30; i += 30) {
            animatedRectangle(i + 30, getHeight() - 30);
            animatedRectangle(0, 0);
            animatedRectangle(i + 30, 0);
            animatedRectangle(0, i + 30);
            animatedRectangle(getWidth() - 30, i + 30);
        }
    }

    /** method that creates one animated rectangle
     *  @param x - positioning the rectangle in width
     *  @param y - positioning the rectangle in height */
    private void animatedRectangle(double x, double y) {
        GRect gRect = new GRect(x, y, 30, 30);
        paintingAndAddingObject(gRect, randomGenerator.nextColor());
    }

    /** a method that colors figures and adds them */
    private void paintingAndAddingObject(GRect gRect, Color color) {
        gRect.setFilled(true);
        gRect.setColor(color);
        gRect.setFillColor(color);
        gRect.sendForward();
        add(gRect);
    }

    /** method that defines and returns the height coordinate of the middle of the application window */
    private double CENTER_OF_APPLICATION_WINDOW_BY_HEIGHT() {
        return (getHeight() - HEIGHT_OF_HORIZONTAL_RECTANGLE_OF_LETTER) / 2.0;
    }

    /** method that defines and returns the width coordinate of the middle of the application window */
    private double CENTER_OF_APPLICATION_WINDOW_BY_WIDTH() {
        return (getWidth() - WIDTH_OF_HORIZONTAL_RECTANGLE_OF_LETTER) / 2.0;
    }
}



