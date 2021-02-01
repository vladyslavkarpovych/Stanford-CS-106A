package com.shpp.p2p.cs.vkarpovych.assignment1;

/**
 * Task 2 - building columns
 */
public class Assignment1Part2 extends FrequentlyRepeatedMethods {

    public void run() throws Exception {
        while (frontIsClear()) {
            buildColumn();
            while (frontIsClear()) {
                moveToAnotherColumn();
                buildColumn();
            }
        }
    }

    private void buildColumn() throws Exception {
        noBeeperPutIt();
        turnLeft();
        while (frontIsClear()) {
            move();
            noBeeperPutIt();
        }
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnLeft();

    }

    /**
     * transition to the next row, located closer to the east
     * @throws Exception If something went wrong.
     */
    private void moveToAnotherColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /**
     * check, if no beeper present under Karel feet, then put it, if statement true
     * @throws Exception If something went wrong.
     */
    private void noBeeperPutIt() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }
}
