package com.shpp.p2p.cs.vkarpovych.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * A program that records the number of minutes spent exercising in the past seven days and determines
 * if enough time has been devoted to exercise to improve cardiovascular health and reduce blood pressure and cholesterol
 */
public class Assignment3Part1 extends TextProgram {

    //a variable that stores the number of aerobic workouts.
    //The value increases if the training duration is more than 30 minutes.
    private static int cardiovascularHealth = 0;

    //a variable that stores the number of times you exercise to maintain low blood pressure.
    //The value increases if the training duration is more than 40 minutes.
    private static int bloodPressure = 0;

    //variable that stores number of days in week
    private static final int NUM_OF_DAYS_IN_WEEK = 7;

    //variables that store the necessary values for a normal workout
    private static final int timeNeededForAerobics = 30;
    private static final int timeNeededForCardiovascularHealth = 40;

    //the required number of aerobic workouts per week
    private static final int NUMBER_AEROBIC_WORKOUTS_PER_WEEK = 5;

    //the required number of cardiovascular workouts per week
    private static final int NUMBER_CARDIOVASCULAR_HEALTH_WORKOUTS_PER_WEEK = 3;

    public void run() {
        workoutDuration();
        summation();
    }

    /**
     * Determine the duration of the training.
     * If the result is satisfactory, we increase the score.
     */
    private void workoutDuration() {
        for (int i = 1; i <= NUM_OF_DAYS_IN_WEEK; i++) {
            print("How many minutes did you do on day " + i + "? ");

            //variable that stores the duration of one workout
            int durationOfTraining = readInt();

            if (durationOfTraining >= timeNeededForAerobics) {
                cardiovascularHealth++;
            }
            if (durationOfTraining >= timeNeededForCardiovascularHealth) {
                bloodPressure++;
            }
        }
    }

    /**
     * The method is designed to display information on the screen containing the research result
     */
    private void summation() {
        println("\n" + "Cardiovascular health:");
        if (cardiovascularHealth >= NUMBER_AEROBIC_WORKOUTS_PER_WEEK) {
            println("    Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("    You needed to train hard for at least " + (NUMBER_AEROBIC_WORKOUTS_PER_WEEK - cardiovascularHealth) + " more day(s) a week!");
        }

        println("Blood pressure:");
        if (bloodPressure >= NUMBER_CARDIOVASCULAR_HEALTH_WORKOUTS_PER_WEEK) {
            println("    Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("    You needed to train hard for at least " + (NUMBER_CARDIOVASCULAR_HEALTH_WORKOUTS_PER_WEEK - bloodPressure) + " more day(s) a week!");
        }
    }
}

