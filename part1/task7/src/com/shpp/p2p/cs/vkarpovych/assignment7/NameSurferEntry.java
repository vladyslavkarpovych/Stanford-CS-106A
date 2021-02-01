package com.shpp.p2p.cs.vkarpovych.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class NameSurferEntry implements NameSurferConstants {

    //variable that stores name
    private String name; //example: Sam
    //array that stores rang of name in each decade
    private int[] rankOfName = new int[NDECADES]; //example: 58 69 99 131 168 236 278 380 467 408 466 997
    //list that stores content of line from file, including name and rank of name in each decade
    List<String> contentOfLineFromFile = new ArrayList<>(); //example:Sam 58 69 99 131 168 236 278 380 467 408 466 997

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        //class which allows us split line into spaces
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()) {
            contentOfLineFromFile.add(stringTokenizer.nextToken());
        }

        //take from the line the numbers which represent decades and add it to array
        IntStream.rangeClosed(1, rankOfName.length).forEach(i -> rankOfName[i - 1] = (Integer.parseInt(contentOfLineFromFile.get(i))));

        //take from the list the first element that stores the name
        name = contentOfLineFromFile.get(0);
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decade >= 0 && decade <= NDECADES ? rankOfName[decade] : 0;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" [");
        for (int temp : rankOfName) {
            stringBuilder.append(temp).append("]");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

