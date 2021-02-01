package com.shpp.p2p.cs.vkarpovych.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Create a program in which a white rectangle is superimposed on four circles.
 */
public class Assignment2Part2 extends WindowProgram {
    private static final int APPLICATION_WIDTH = 300;
    private static final int APPLICATION_HEIGHT = 300;

    // for Windows users
    private static final int WINDOWS_MARGIN = 23;
    private static final int RADIUS_OF_OVAL = 60;
    private static final int DIAMETER = RADIUS_OF_OVAL * 2;

    @Override
    public void run() {
        drawAllOvals();
        drawRect();
    }

    //display all created ovals
    private void drawAllOvals() {
        drawingOvals(0);
        int twoBottomOvals = APPLICATION_HEIGHT - WINDOWS_MARGIN - DIAMETER;
        drawingOvals(twoBottomOvals);
    }

    //a method that draws ovals with a height depending on the input parameter
    private void drawingOvals(int height) {
        for (int i = 0; i < 2; i++) {
            GOval oval = new GOval(i * (APPLICATION_WIDTH - DIAMETER), height, DIAMETER, DIAMETER);
            oval.setFilled(true);
            oval.setFillColor(Color.BLACK);
            add(oval);
        }
    }

    //create and draw rectangle
    private void drawRect() {
        GRect gRect = new GRect(RADIUS_OF_OVAL, RADIUS_OF_OVAL, APPLICATION_WIDTH - DIAMETER, APPLICATION_HEIGHT - DIAMETER - WINDOWS_MARGIN);
        gRect.setFillColor(Color.WHITE);
        gRect.setColor(Color.WHITE);
        gRect.setFilled(true);
        gRect.sendToFront();
        add(gRect);
    }
}