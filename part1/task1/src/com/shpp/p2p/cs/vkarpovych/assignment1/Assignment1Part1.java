package com.shpp.p2p.cs.vkarpovych.assignment1;


/**
 * Task 1 - pick up a newspaper and return to the starting position
 */
public class Assignment1Part1 extends FrequentlyRepeatedMethods {
    public void run() throws Exception {
        moveToPickBeeper();
        pickBeeper();
        moveToStartPosition();
    }

    /**
     * Karel goes for the newspaper
     * @throws Exception if something went wrong
     */
    public void moveToPickBeeper() throws Exception {
        moveIfFrontIsClear();
        turnRight();
        while (leftIsBlocked()) {
            move();
        }
        turnLeft();
        while (noBeepersPresent()) {
            move();
        }
    }

    /**
     * return back to origin position
     * @throws Exception If something went wrong.
     */
    public void moveToStartPosition() throws Exception {
        turnAround();
        moveIfFrontIsClear();
        turnRight();
        moveIfFrontIsClear();
    }
}

