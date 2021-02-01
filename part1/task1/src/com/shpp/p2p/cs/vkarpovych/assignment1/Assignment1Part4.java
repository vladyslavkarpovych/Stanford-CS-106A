package com.shpp.p2p.cs.vkarpovych.assignment1;

/**
 * Task 4 - Arrange the beepers in the form of a checkerboard.
 */
public class Assignment1Part4 extends FrequentlyRepeatedMethods {

    public void run() throws Exception {
        /* According to the condition, the beeper
         must be located in the southwest corner */
        putBeeper();
        oneWidthWorld();
    }

    /**
     * Karel's actions on each cell of map.
     * @throws Exception If something went wrong.
     */
    private void cellActions() throws Exception {
        if (noBeepersPresent()) {
            move();
            putBeeper();
        } else {
            move();
        }
    }

    /**
     * Karel's movement up to edge of map.
     * @throws Exception If something went wrong.
     */
    private void moveToEdge() throws Exception {
        while (frontIsClear()) {
            cellActions();
        }
    }

    /**
     * Karel's movement from left to right edge of map.
     * @throws Exception If something went wrong.
     */
    private void moveToEast() throws Exception {
        moveToEdge();
        turnLeft();

        if (frontIsClear()) {
            cellActions();
            turnLeft();
        }
    }

    /**
     * Karel's movement from right to left edge of map.
     * @throws Exception If something went wrong.
     */
    private void moveToWest() throws Exception {
        moveToEdge();
        turnRight();

        if (frontIsClear()) {
            cellActions();
            turnRight();
        }
    }

    /**
     * @throws Exception if something went wrong
     */
    private void oneWidthWorld() throws Exception {
        /* the condition will be met
        if the world consists of one cell with the width */
        if (frontIsBlocked()) {
            turnLeft();
            moveToEdge();
        } else {
            while (frontIsClear()) {
                moveToEast();
                moveToWest();
            }
        }
    }
}