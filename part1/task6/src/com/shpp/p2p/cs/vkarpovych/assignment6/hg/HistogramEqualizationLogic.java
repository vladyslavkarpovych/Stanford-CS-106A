package com.shpp.p2p.cs.vkarpovych.assignment6.hg;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        //an array of 256 integer numbers, one for each possible degree of brightness,
        //where each element of the array represents the number of pixels that have the corresponding brightness.
        //example -> the zero element of the array is the number of pixels with a brightness of 0, the first - with a brightness of 1
        int[] degreeOfBrightnessOfEachPixel = new int[256];

        Arrays.stream(luminances).forEach(luminance ->
                IntStream.range(0, luminances[0].length).
                        forEach(j -> degreeOfBrightnessOfEachPixel[luminance[j]]++));
        return degreeOfBrightnessOfEachPixel;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] cumulativeFrequenciesOfImage = new int[histogram.length];
        cumulativeFrequenciesOfImage[0] = histogram[0];
        IntStream.range(1, cumulativeFrequenciesOfImage.length)
                .forEach(i -> cumulativeFrequenciesOfImage[i] = cumulativeFrequenciesOfImage[i - 1] + histogram[i]);
        return cumulativeFrequenciesOfImage;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        int[] histogram = cumulativeSumFor(histogramFor(luminances));
        int amountNumberOfPixels = totalPixelsIn(luminances);

        int i = 0;
        while (i < luminances.length) {
            int j = 0;
            while (j < luminances[0].length) {
                luminances[i][j] = MAX_LUMINANCE * histogram[luminances[i][j]] / amountNumberOfPixels;
                j++;
            }
            i++;
        }
        return luminances;
    }
}
