package com.shpp.p2p.cs.vkarpovych.assignment6.tm;

import java.util.stream.IntStream;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        //music track
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        identifyActiveCells(result, toneMatrix, column, samples);
        return waveNormalize(result);
    }

    /**
     * A method that detects active cells and combines them into one track
     *
     * @param result     music track
     * @param toneMatrix a two-dimensional array that is a cell in a matrix. True means that the cell is on, false - off.
     * @param column     contains the index of the currently playing column
     * @param samples    the sound that is played
     */
    private static void identifyActiveCells(double[] result, boolean[][] toneMatrix, int column, double[][] samples) {
        int i = 0;
        //determine the active cells
        while (i < toneMatrix.length) {
            if (toneMatrix[i][column]) {
                int j = 0;
                while (j < result.length) {
                    //summation of sounds
                    result[j] += samples[i][j];
                    j++;
                }
            }
            i++;
        }
    }

    /**
     * sound normalization method in the range [-1.0; 1.0]
     * @param result music track
     * @return normalized sound of the required range
     */
    private static double[] waveNormalize(double[] result) {
        double maximumValue = 0.0;
        double minimumValue = 0.0;

        for (double currentNote : result) {
            if (currentNote < -1.0 || currentNote > 1.0) {
                if (currentNote < minimumValue) {
                    minimumValue = currentNote;
                } else if (currentNote > maximumValue) {
                    maximumValue = currentNote;
                }
            }
        }
        searchWaveWithHighestIntensity(result, maximumValue, minimumValue);
        return result;
    }

    /**
     * highest intensity wave search method
     * @param result music track
     * @param maximumValue represent maximum value of highest intensity of wave of sound
     * @param minimumValue represent minimum value of highest intensity of wave of sound
     * @return wave with highest intensity
     */
    private static double[] searchWaveWithHighestIntensity(double[] result, double maximumValue, double minimumValue) {
        //variable that store highest intensity of waves
        double highestIntensity;
        if (highestIntensity(maximumValue, minimumValue) == 0) {
            return result;
        } else if (highestIntensity(maximumValue, minimumValue) < 0) {
            highestIntensity = minimumValue;
        } else {
            highestIntensity = maximumValue;
        }
        putWavesInRange(result, highestIntensity);
        return result;
    }

    /**
     * Adjust waves to the necessary range [-1.0; 1.0]
     *
     * @param result  music track
     * @param highestIntensity the largest value that needs to be divided to get the required range
     */
    private static void putWavesInRange(double[] result, double highestIntensity) {
        IntStream.range(0, result.length).forEachOrdered(i -> result[i] /= highestIntensity);
    }

    /**
     * Define highest intensity of waves
     * @param maxValue represent maximum value of highest intensity of wave of sound
     * @param minValue represent minimum value of highest intensity of wave of sound
     * @return highest intensity of wave
     */
    private static double highestIntensity(double maxValue, double minValue) {
        return maxValue + minValue;
    }
}

