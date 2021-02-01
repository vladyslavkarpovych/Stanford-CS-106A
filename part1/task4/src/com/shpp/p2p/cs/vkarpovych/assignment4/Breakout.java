package com.shpp.p2p.cs.vkarpovych.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * About game: The top third of the game screen is the occupied row of bars. A ball moves across the screen, bouncing off the top and side edges of the screen.
 * When hitting the bar, the ball bounces off and the bar disappears. The player loses one life when the ball hits the bottom of the screen;
 * To prevent this, the player has a movable paddle that can be thrown back to the top of the screen.
 */

public class Breakout extends WindowProgram {

    /** Width and height of application window in pixels
     *  Optimal values for APPLICATION_WIDTH >= 400
     *  Optimal values for APPLICATION_HEIGHT >= 500 */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 500;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 40;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static int NTURNS = 3;

    /** Ball velocity */
    private double vx, vy;

    /** variables that stores min, max values of speed for x and recommended value for y */
    private static final double VELOCITY_MIN_FOR_X = 1.0;
    private static final double VELOCITY_MAX_FOR_X = 3.0;
    private static final double RECOMMENDED_VELOCITY_FOR_Y = 3.0;

    /** frame per second */
    private static final int FPS = 120;
    /** animation delay */
    private static final int PAUSE_TIME = 1000 / FPS;

    /** references to objects of a certain class, from which the object will be created */
    private static GOval ball;
    private static GRect paddle;
    private static GLabel gLabel, attempts;

    /** diameter of ball */
    private static final int DIAMETER_OF_BALL = BALL_RADIUS * 2;

    /** variables that stores the text that is displayed on the screen */
    private static final String YOU_WON = "You won";
    private static final String GAME_OVER = "Game over";

    /** total number of bricks */
    private static int brickCounter = NBRICK_ROWS * NBRICKS_PER_ROW;

    /** variable for storing program duration in milliseconds */
    private static long durationOfProgramInMs;

    /** starting point of executing program */
    public void run() {
        uploadAllNeededMethods();
    }

    /** method that upload all methods needed for executing program */
    private void uploadAllNeededMethods() {
        clickForStart();
        printCountOfBricks();
        displayObjects();
        numberOfAttempts();

        //determine the start time of the program
        long programLaunchTime = System.currentTimeMillis();
        for (int i = 0; i < NTURNS; i++) {
            playGame();
            remove(gLabel);

            if (brickCounter == 0) {
                removeAll();
                //determine the end time of the program
                long programEndTime = System.currentTimeMillis();
                //calculate the difference between the end and start time
                durationOfProgramInMs = (int) programEndTime - (int) programLaunchTime;
                durationOfProgram();
                displayMessage(YOU_WON);
                break;
            } else {
                removeAll();
            }
        }
    }

    /** a method that draws bricks based on their number and color */
    private void createBricks() {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int column = 0; column < NBRICKS_PER_ROW; column++) {
                //adding an individual brick object
                GRect brick = new GRect(column * (BRICK_WIDTH + BRICK_SEP), row * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                add(brick);

                //setting colors depending on which row the bricks are in
                switch (row) {
                    case 0:
                    case 1:
                        brick.setColor(Color.RED);
                        break;
                    case 2:
                    case 3:
                        brick.setColor(Color.ORANGE);
                        break;
                    case 4:
                    case 5:
                        brick.setColor(Color.YELLOW);
                        break;
                    case 6:
                    case 7:
                        brick.setColor(Color.GREEN);
                        break;
                    case 8:
                    case 9:
                        brick.setColor(Color.CYAN);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /** method that creates the paddle */
    private void createPaddle() {
        paddle = new GRect(getWidth() / 2.0 - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
        addMouseListeners();
    }

    /** method that creates the ball */
    private void createBall() {
        ball = new GOval(getWidth() / 2.0 - BALL_RADIUS, getHeight() / 2.0 - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
    }

    /** method that creates the inscription "Duration of game in seconds: + seconds" */
    private void durationOfProgram() {
        final String DURATION_OF_GAME = "Duration of game in seconds: ";
        String runningTime = DURATION_OF_GAME + conversionInSeconds(durationOfProgramInMs);
        createGLabel(runningTime, getWidth() / 2.0,
                getHeight() / 2.0);
    }

    /** a method that display objects (bricks, paddle, ball) required for the application to function */
    private void displayObjects() {
        createBricks();
        createPaddle();
        createBall();
    }

    /** a method that makes the sound of a ball hitting a racket or brick */
    private void sound() {
        // path to the file that contains the audio recording //
        final String PATH = "C:\\Users\\user\\Desktop\\sound.wav";
        double[] clip = StdAudio.read(PATH);
        StdAudio.play(clip);
    }

    /** a method to move the paddle, by tracking the mouse of the player
     *  @param e - class object reference MouseEvent */
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2.0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }

    /** method that creates the inscription "Click left button of mouse for start",
     *  then waiting when user do that, and remove inscription */
    private void clickForStart() {
        final String CLICK = "Click left button of mouse for start";
        createGLabel(CLICK, getWidth() / 2.0, getHeight() / 2.0);
        waitForClick();
        remove(gLabel);
    }

    private void playGame() {
        velocityOfBall();
        //until the ball hits the bottom border of the application window
        //and the number of bricks is not zero, the ball continues to move
        while (!(ball.getY() >= getHeight()) && brickCounter != 0) {
            moveBall();
        }
    }

    /** method that moves the ball */
    private void moveBall() {

        ball.move(vx, vy);
        checkWalls();

        GObject collider = getCollidingObject();
        if (collider != gLabel) {
            if (collider == paddle) {
                if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - DIAMETER_OF_BALL & ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - DIAMETER_OF_BALL + vy) {
                    vy = -vy;
                    sound();
                }
            } else if (collider != null && collider != attempts) {
                remove(collider);
                vy = -vy;
                brickCounter--;
                remove(gLabel);
                printCountOfBricks();
            }
        }

        pause(PAUSE_TIME);
    }

    /** the method that will be executed if the ball hits the lower bound, that is, when the round is lost */
    private void lostCurrentRound() {
        ball.setLocation(getWidth() / 2.0, getHeight() / 2.0);
        NTURNS--;
        numberOfAttempts();
        if (NTURNS <= 0) {
            removeAll();
            displayMessage(GAME_OVER);
        }
    }

    /** method that converts milliseconds to seconds
     *  @param time - the duration of the program in milliseconds
     *  @return - the duration of the program in seconds */
    private float conversionInSeconds(float time) {
        //variable that stores 1000 ms or 1 second
        final int MILLISECONDS = 1000;
        return time / MILLISECONDS;
    }

    /** method that determines velocity of the ball */
    private void velocityOfBall() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vy = RECOMMENDED_VELOCITY_FOR_Y;
        vx = rgen.nextDouble(VELOCITY_MIN_FOR_X, VELOCITY_MAX_FOR_X);
        //with a probability of 50%, changes the sign of the obtained number to minus
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
    }

    /** check for collision of ball with walls (right, left, top),
     *  except for the bottom border of the application window */
    private void checkWalls() {
        if (ball.getX() <= 0 || ball.getX() + DIAMETER_OF_BALL >= APPLICATION_WIDTH) {
            vx = -vx;
        } else if (ball.getY() <= 0) {
            vy = -vy;
        } else if (ball.getY() + DIAMETER_OF_BALL > getHeight()) {
            lostCurrentRound();
        }
    }

    /**a method that checks if the ball hit some object
     * @return object if collision occurs. Otherwise (if there are no objects present), return null */
    private GObject getCollidingObject() {
        if ((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt((ball.getX() + DIAMETER_OF_BALL), ball.getY()) != null) {
            return getElementAt(ball.getX() + DIAMETER_OF_BALL, ball.getY());
        } else if (getElementAt(ball.getX(), (ball.getY() + DIAMETER_OF_BALL)) != null) {
            return getElementAt(ball.getX(), ball.getY() + DIAMETER_OF_BALL);
        } else if (getElementAt((ball.getX() + DIAMETER_OF_BALL), (ball.getY() + DIAMETER_OF_BALL)) != null) {
            return getElementAt(ball.getX() + DIAMETER_OF_BALL, ball.getY() + DIAMETER_OF_BALL);
        } else {
            return null;
        }
    }

    /** a method that allows you to create inscriptions on the screen (game over, you won, number of bricks and others)
     *  @param text   - text signature
     *  @param width  - the location of the label at the x coordinate
     *  @param height - the location of the label at the y coordinate */
    private void createGLabel(String text, double width, double height) {
        gLabel = new GLabel(text, width, height);
        settingsOfGLabel();
    }

    /** a method that allows to reduce the total number of lines of code by issuing repetitive commands */
    private void settingsOfGLabel() {
        gLabel.move(-gLabel.getWidth() / 2, gLabel.getHeight());
        gLabel.setColor(Color.RED);
        add(gLabel);
    }

    /** method that creates the inscription "Count of bricks" */
    private void printCountOfBricks() {
        final String COUNT_OF_BRICKS = "Count of bricks: ";
        gLabel = new GLabel(COUNT_OF_BRICKS + "" + Long.toString(brickCounter),
                getWidth() / 2.0 + gLabel.getFontMetrics().stringWidth(COUNT_OF_BRICKS + Long.toString(brickCounter)) / 2.0,
                getHeight() - gLabel.getFontMetrics().getHeight());
        gLabel.setColor(Color.RED);
        add(gLabel);
    }

    /** method that creates the inscription "Number of attempts" */
    private void numberOfAttempts() {
        //a variable that stores the value required to offset the label from the center to left
        final double INDENT_FROM_CENTRE = 7.0;
        final String NUMBER_OF_ATTEMPTS = "Number of attempts: ";
        if (attempts != null) remove(attempts);
        attempts = new GLabel(NUMBER_OF_ATTEMPTS + NTURNS, getWidth() / 2.0 - NUMBER_OF_ATTEMPTS.length() * INDENT_FROM_CENTRE, getHeight() - gLabel.getFontMetrics().getHeight());
        attempts.setColor(Color.RED);
        add(attempts);
    }

    /** method which create GLabel and display text
     *  @param text - phrase to be displayed */
    private void displayMessage(String text) {
        gLabel = new GLabel(text);
        gLabel.setColor(Color.RED);
        add(gLabel, getWidth() / 2.0 - gLabel.getWidth() / 2,
                getHeight() / 2.0);
    }
}