package com.shpp.p2p.cs.vkarpovych.assignment1;

/**
 * Task 3 - Finding the Middle
 */
public class Assignment1Part3 extends FrequentlyRepeatedMethods {

    public void run() throws Exception {
        if (frontIsBlocked()) {
            putBeeper();
        } else {
            fillFirstAndLastCells();
            centralShift();
        }

    }

    /**
     * put a beeper on the first and last cell
     * @throws Exception if something went wrong
     */
    private void fillFirstAndLastCells() throws Exception {
        putBeeper();

        while (frontIsClear()) {
            move();
        }
        turnAround();
        putBeeper();
    }

    /**
     * gradual shift of beepers towards the center of the field
     * @throws Exception if something went wrong
     */
    private void centralShift() throws Exception {
        while (frontIsClear()) {
            move();
            if (beepersPresent()) {
                pickBeeper();
                turnAround();
                move();
                if (!beepersPresent()) {
                    putBeeper();
                }
            }
        }
    }
}


