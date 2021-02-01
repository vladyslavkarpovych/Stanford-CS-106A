package com.shpp.p2p.cs.vkarpovych.assignment6.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] arrayOfPixelsOfMulticolorImage = source.getPixelArray();
        boolean[][] blackWhiteImage = new boolean[arrayOfPixelsOfMulticolorImage.length][arrayOfPixelsOfMulticolorImage[0].length];

        int i = 0;
        while (i < arrayOfPixelsOfMulticolorImage.length) {
            int j = 0;
            while (j < arrayOfPixelsOfMulticolorImage[0].length) {
                blackWhiteImage[i][j] = ((getRedColor(arrayOfPixelsOfMulticolorImage, i, j) % 2) != 0);
                j++;
            }
            i++;
        }
        return blackWhiteImage;
    }


    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] arrayOfPixelsOfMulticolorImage = source.getPixelArray();
        int i = 0;
        while (i < arrayOfPixelsOfMulticolorImage.length) {
            int j = 0;
            while (j < arrayOfPixelsOfMulticolorImage[0].length) {
                if (!message[i][j]) {
                    arrayOfPixelsOfMulticolorImage[i][j] = makeRedChannelEven(arrayOfPixelsOfMulticolorImage[i][j]);
                } else {
                    arrayOfPixelsOfMulticolorImage[i][j] = makeRedChannelOdd(arrayOfPixelsOfMulticolorImage[i][j]);
                }
                arrayOfPixelsOfMulticolorImage[i][j] = GImage.createRGBPixel(
                        getRedColor(arrayOfPixelsOfMulticolorImage, i, j),
                        getGreenColor(arrayOfPixelsOfMulticolorImage, i, j),
                        getBlueColor(arrayOfPixelsOfMulticolorImage, i, j));
                j++;
            }
            i++;
        }
        return new GImage(arrayOfPixelsOfMulticolorImage);
    }

    /**
     * Method which return red component of pixel
     *
     * @param array which consist pixels
     * @param i     - row
     * @param j     - column
     */
    private static int getRedColor(int[][] array, int i, int j) {
        return GImage.getRed(array[i][j]);
    }

    /**
     * Method which return red component of pixel
     */
    private static int getRedColor(int pixel) {
        return GImage.getRed(pixel);
    }

    /**
     * Method which return green component of pixel
     *
     * @param array which consist pixels
     * @param i     - row
     * @param j     - column
     */
    private static int getGreenColor(int[][] array, int i, int j) {
        return GImage.getGreen(array[i][j]);
    }

    /**
     * Method which return green component of pixel
     */
    private static int getGreenColor(int pixel) {
        return GImage.getGreen(pixel);
    }

    /**
     * Method which return blue component of pixel
     *
     * @param array which consist pixels
     * @param i     - row
     * @param j     - column
     */
    private static int getBlueColor(int[][] array, int i, int j) {
        return GImage.getBlue(array[i][j]);
    }

    /**
     * Method which return blue component of pixel
     */
    private static int getBlueColor(int pixel) {
        return GImage.getBlue(pixel);
    }

    /**
     * Method which return alpha component of pixel
     */
    private static int getAlphaColor(int pixel) {
        return GImage.getAlpha(pixel);
    }

    /**
     * a method which make the red channel value unpaired, if the secret pixel is black and represented by the value true
     *
     * @param pixel of image
     * @return RGB pixel with updated value of red component
     */
    private static int makeRedChannelOdd(int pixel) {
        int redComponent = getRedColor(pixel);
        int updatedValueOfRedComponent = redComponent % 2 == 0 ? redComponent + 1 : redComponent;
        return GImage.createRGBPixel(updatedValueOfRedComponent, getGreenColor(pixel), getBlueColor(pixel), getAlphaColor(pixel));
    }

    /**
     * a method which make the red channel value paired, if the secret pixel is white and represented by the value false
     *
     * @param pixel pixel of image
     * @return RGB pixel with updated value of red component
     */
    private static int makeRedChannelEven(int pixel) {
        int redComponent = getRedColor(pixel);
        int updatedValueOfRedComponent = redComponent % 2 == 1 ? redComponent - 1 : redComponent;
        return GImage.createRGBPixel(updatedValueOfRedComponent, getGreenColor(pixel), getBlueColor(pixel), getAlphaColor(pixel));
    }
}