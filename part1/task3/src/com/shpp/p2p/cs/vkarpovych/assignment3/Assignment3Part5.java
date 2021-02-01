package com.shpp.p2p.cs.vkarpovych.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

/**
 * St. Petersburg game. Two people play: lucky and sweaty.
 * The game ends when the first one earns $ 20 or more.
 * Sweaty puts $ 1 on the table, and the lucky one starts tossing a coin.
 * If the eagle - then sweaty adds to the amount on the table exactly the same amount.
 * If the tail - everything on the table, goes to the lucky one.
 * If the lucky winner is less than $ 20, the game is repeated.
 */

public class Assignment3Part5 extends TextProgram {

    //amount of money required to complete the game
    private static final int REQUIRED_AMOUNT_OF_MONEY = 20;

    //variable that stores how many player earned in this game
    private static final String EARNED_OF_PLAYER = "This game, you earned $";

    //variable that stores player current sum
    private static final String CURRENT_SUM_OF_PLAYER = "Your total is $";

    //class that generates a random value
    private static final RandomGenerator randomGenerator = RandomGenerator.getInstance();

    //variable that stores the number of games played for which $20 was made
    private static int countOfGames = 0;

    public void run() {
        playGame();
    }

    /** Method that contains the main logic of the program.
     *  Depending on the value of the boolean type, further actions unfold */
    private void playGame() {
        //total amount of money on the table
        int amountSumOnTable = 1;
        //player's current money
        int currentTotalPlayerMoney = 0;

        while (currentTotalPlayerMoney < REQUIRED_AMOUNT_OF_MONEY) {
            if (isHeads()) {
                //adds to the amount on the table exactly the same
                amountSumOnTable += amountSumOnTable;
            } else {
                // increase current sum for sum from the table
                currentTotalPlayerMoney += amountSumOnTable;
                // print how many player earned in this game
                println(EARNED_OF_PLAYER + amountSumOnTable);
                // print player current sum
                println(CURRENT_SUM_OF_PLAYER + currentTotalPlayerMoney);
                //update the amount of money on the table to 1
                amountSumOnTable = 1;
                //increase the count of games
                countOfGames++;
            }
        }
        println("It took " + countOfGames + " games to earn $20");
    }

    /** Method that generates true or false **/
    private boolean isHeads() {
        return randomGenerator.nextBoolean();
    }
}
