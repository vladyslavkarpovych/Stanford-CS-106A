package com.shpp.p2p.cs.vkarpovych.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draw the greatest caterpillar in the world. The ovals should overlap in the correct order
 */
public class Assignment2Part6 extends WindowProgram {
    //total number segments of caterpillar
    private static final int COUNT_0F_CIRCLES = 20;
    //size of one segment of caterpillar
    private static final int SIZE_OF_CIRCLE = 80;
    //distance between the beginning of the previous and next segment of caterpillar
    private static final int DISTANCE_BETWEEN_CIRCLES = 30;
    //the height at which the bottom segments of caterpillar are located
    private static final int HEIGHT_OF_LOCATION_OF_BOTTOM_CIRCLES = 40;
    private static final int HEIGHT_OF_LOCATION_OF_TOP_CIRCLES = 0;
    final int WIDTH_OF_APPLICATION_WINDOW = SIZE_OF_CIRCLE * COUNT_0F_CIRCLES;

    public void run() {
        //variables for storing sizes of application window
        final int HEIGHT_OF_APPLICATION_WINDOW = getHeight() / 3 + SIZE_OF_CIRCLE;

        //set sizes of application window
        setSize(WIDTH_OF_APPLICATION_WINDOW, HEIGHT_OF_APPLICATION_WINDOW);

        for (int i = 0; i < COUNT_0F_CIRCLES; i++) {
            createCircle(DISTANCE_BETWEEN_CIRCLES * i,
                    i % 2 == 0 ? HEIGHT_OF_LOCATION_OF_BOTTOM_CIRCLES : HEIGHT_OF_LOCATION_OF_TOP_CIRCLES);
        }
    }

    /**
     * Method which create a circle (segment of caterpillar)
     * @param coordinateX - variable that store distance between the beginning of the previous and next segment of caterpillar
     * @param coordinateY - variable storing the height at which a particular segment of caterpillar is located
     */
    private void createCircle(int coordinateX, int coordinateY) {
        GOval gOval = new GOval(coordinateX, coordinateY, SIZE_OF_CIRCLE, SIZE_OF_CIRCLE);
        gOval.setFilled(true);
        gOval.setColor(Color.RED);
        gOval.setFillColor(Color.GREEN);
        add(gOval);
    }
}
