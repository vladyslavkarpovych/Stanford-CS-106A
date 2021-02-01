package com.shpp.p2p.cs.vkarpovych.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/**
 * A program that creates a pyramid of bricks. Each row contains 1 brick less.
 * The pyramid should be centered horizontally and lie on the "bottom" of the window.
 */
public class Assignment3Part4 extends WindowProgram {

    /** Width of each brick in pixels */
    private static final double BRICK_WIDTH = 30;

    /** Height of each brick in pixels */
    private static final double BRICK_HEIGHT = 15;

    /** Number of bricks in the base of the pyramid */
    private static final int BRICKS_IN_BASE = 20;

    /** Coordinates of the bricks in the leftmost every row */
    private static double originalX, originalY;

    @Override
    public void run() {
        for (int i = BRICKS_IN_BASE; i > 0; i--) {
            createBrickRow(i);
        }
    }

    /**
     * method for drawing bricks
     * @param i - variable that store count of rows
     */
    private void createBrickRow(int i) {
        //define the starting point for drawing bricks
        startingPoint(i);

        //then we start drawing bricks
        for (int k = 0; k < i; k++) {
            double x, y;
            x = originalX + k * BRICK_WIDTH;
            y = originalY;
            GRect gRect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
            gRect.setFilled(true);
            if(k % 2 != 0) {
                gRect.setFillColor(Color.GREEN);
            } else {
                gRect.setFillColor(Color.RED);
            }
            add(gRect);
        }
    }

    /**
     *A method that determines the starting point for drawing bricks in each row
     * default getWidth = 754
     * default getHeight = 469
     */
    private void startingPoint(int j) {
        originalX = (getWidth() - j * BRICK_WIDTH) / 2;
        originalY = getHeight() - BRICK_HEIGHT - (BRICKS_IN_BASE - j) * BRICK_HEIGHT;
    }
}
