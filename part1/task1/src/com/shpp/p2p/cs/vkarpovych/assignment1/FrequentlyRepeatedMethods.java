package com.shpp.p2p.cs.vkarpovych.assignment1;

import com.shpp.karel.KarelTheRobot;

public class FrequentlyRepeatedMethods extends KarelTheRobot {
    /**
     * turn 180 degrees
     * @throws Exception If something went wrong.
     */
    public void turnAround() throws Exception {
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }

    /**
     * turn 270 degrees left and it's equal turn right
     * @throws Exception If something went wrong.
     */
    public void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    public void moveIfFrontIsClear() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    public void putBeeperIfFrontIsBlocked() throws Exception {
        if(frontIsBlocked()) {
            putBeeper();
        }
    }
}
