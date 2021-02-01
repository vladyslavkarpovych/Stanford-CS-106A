package com.shpp.p2p.cs.vkarpovych.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * It is necessary to draw a matrix of black boxes separated by "streets" in the centre of app window.
 */
public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;
    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;
    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public static final int APPLICATION_WIDTH = 550;
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        createMatrix();
    }

    // draw matrix of squares with set number of rows an columns in the center of screen
    private void createMatrix() {
        double sumOfBoxAndSpacing = BOX_SIZE + BOX_SPACING;
        double topIndent = (getHeight() - sumOfBoxAndSpacing * NUM_COLS + BOX_SPACING) / 2;
        double leftIndent = (getWidth() - sumOfBoxAndSpacing * NUM_ROWS + BOX_SPACING) / 2;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                createBox(i * sumOfBoxAndSpacing + leftIndent, j * sumOfBoxAndSpacing + topIndent);
            }
        }

    }

    // create rectangle with set size and position
    private void createBox(double coordinateX, double coordinateY) {
        GRect rect = new GRect(coordinateX, coordinateY, BOX_SIZE, BOX_SIZE);
        rect.setColor(Color.BLACK);
        rect.setFilled(true);
        rect.setFillColor(Color.BLACK);
        add(rect);
    }
}
