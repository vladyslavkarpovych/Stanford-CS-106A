package com.shpp.p2p.cs.vkarpovych.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Write a program that draws one of the suggested flags
 */

public class Assignment2Part4 extends WindowProgram {

    private static final int FLAG_WIDTH = 300;
    private static final int FLAG_HEIGHT = 300;
    public static final int APPLICATION_WIDTH = 750;
    public static final int APPLICATION_HEIGHT = 700;
    //variable which storing color of first rectangle
    private static final Color FIRST_COLOR = Color.BLACK;
    //variable which storing color of second rectangle
    private static final Color SECOND_COLOR = Color.YELLOW;
    //variable which storing color of third rectangle
    private static final Color THIRD_COLOR = Color.RED;
    private static final int SIZE_OF_FONT = 25;
    private static final int INDENT_TEXT_FROM_BOTTOM_EDGE = 10;
    private static final int INDENT_TEXT_FROM_RIGHT_EDGE = 5;
    private static final int POSITION_OF_FIRST_RECTANGLE = 0;
    private static final int POSITION_OF_SECOND_RECTANGLE = FLAG_WIDTH / 3;
    private static final int POSITION_OF_THIRD_RECTANGLE = FLAG_WIDTH / 3 * 2;
    //width of one of the three existing rectangles
    private static final double WIDTH_OF_ONE_RECTANGLE = FLAG_WIDTH / 3.0;

    public void run() {
        drawFlag(FIRST_COLOR, SECOND_COLOR, THIRD_COLOR);
        signName();
    }

    /**
     * A method that draws the entire country flag, taking the colors of each rectangle
     * @param firstColor  - variable that storing color of first rectangle
     * @param secondColor - variable that storing color of second rectangle
     * @param thirdColor  - variable that storing color of third rectangle
     */
    private void drawFlag(Color firstColor, Color secondColor, Color thirdColor) {
        createRectangle(firstColor, POSITION_OF_FIRST_RECTANGLE);
        createRectangle(secondColor, POSITION_OF_SECOND_RECTANGLE);
        createRectangle(thirdColor, POSITION_OF_THIRD_RECTANGLE);
    }

    /**
     * Method is designed to create a rectangle that the flag consists of
     * @param color    - variable which take a color of one rectangle
     * @param position - variable that defines the location of the rectangle
     */
    private void createRectangle(Color color, int position) {
        GRect gRect = new GRect((getWidth() - FLAG_WIDTH) / 2.0 + position, (getHeight() - FLAG_HEIGHT) / 2.0, WIDTH_OF_ONE_RECTANGLE, FLAG_HEIGHT);
        gRect.setFilled(true);
        gRect.setColor(color);
        gRect.setFillColor(color);
        add(gRect);
    }

    /** Method is designed to display information about the name of the country */
    private void signName() {
        GLabel gLabel = new GLabel("Flag of Belgium");
        gLabel.setFont(new Font("Verdana", 1, SIZE_OF_FONT));
        gLabel.setColor(Color.BLACK);
        add(gLabel, getWidth() - gLabel.getWidth() - INDENT_TEXT_FROM_RIGHT_EDGE, getHeight() - INDENT_TEXT_FROM_BOTTOM_EDGE);
    }
}
