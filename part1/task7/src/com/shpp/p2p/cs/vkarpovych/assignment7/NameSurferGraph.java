package com.shpp.p2p.cs.vkarpovych.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

    //a list that contains incoming names and their rankings in each decade
    private ArrayList<NameSurferEntry> nameSurferEntry;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        nameSurferEntry = new ArrayList<>();
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        nameSurferEntry.clear();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        nameSurferEntry.add(entry);
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        displayGrid();
        displayGraph();
    }

    /**
     * method that display on the screen created grid
     */
    private void displayGrid() {
        drawVerticalLines();
        drawHorizontalLines();
    }

    /**
     * method that draws vertical lines of grid
     */
    private void drawVerticalLines() {
        IntStream.range(0, NDECADES).forEachOrdered(i -> {
            drawLine(i * (float) (getWidth() / NDECADES), 0, (float) (getWidth() / NDECADES) * i, getHeight());
            drawYears(i);
        });
    }

    /**
     * method that draws horizontal lines (top and bottom) of grid
     */
    private void drawHorizontalLines() {
        //top horizontal line
        drawLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        //bottom horizontal line
        drawLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
    }

    /**
     * method that draws years
     * @param numberOfDecade - a number in the range from 0 to 10 that represents the decade number
     */
    private void drawYears(int numberOfDecade) {
        //width of one of the twelve compartments
        float widthOfCompartment = (float) (getWidth() / NDECADES) / 2;

        GLabel year = new GLabel(String.valueOf(START_DECADE + (DECADE * numberOfDecade)));
        add(year, (float) (numberOfDecade * (getWidth() / NDECADES) + widthOfCompartment - (year.getWidth()) / 2.0), getHeight() - year.getDescent());
    }

    /**
     * method that draws input name and value that indicates the rank in each decade
     * @param entry represent name which should be display on the graph
     * @param color of graph
     * @param nextSegmentByX represent next one of the twelve compartments by x
     * @param nextSegmentByY represent next one of the twelve compartments by y
     * @param i represent number of decade
     */
    private void drawInfo(NameSurferEntry entry, Color color, int nextSegmentByX, int nextSegmentByY, int i) {
        GLabel name = new GLabel(" " + entry.getName() + " " + rankOfName(entry.getRank(i)));
        name.setColor(color);
        add(name, nextSegmentByX, nextSegmentByY);
    }

    /**
     * method that display on the screen full-fledged graph (with names, ranks and lines)
     */
    private void displayGraph() {
        //an array, depending on the number of which the color of the graph is selected
        int[] array = {0};
        for (NameSurferEntry nameEntry : nameSurferEntry) {
            createFullFledgedGraph(nameEntry,
                    rangeOfAvailableColors()[array[0]++ % rangeOfAvailableColors().length]);
        }
    }

    /**
     * method that contains the range of available colors on the basis of which the graph is drawn
     * @return available colors
     */
    private Color[] rangeOfAvailableColors() {
        return new Color[]{Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
    }

    /**
     * a method that create a full-fledged graph, including names,
     * their rankings in each decade, and the lines themselves that correspond to the values
     * @param entry represent name, which should be displayed
     * @param color of graph lines
     */
    private void createFullFledgedGraph(NameSurferEntry entry, Color color) {
        //a variable that stores the location of the previous segment (decade) by x
        int previousSegmentByX = 0;
        //a variable that stores the location of the previous segment (decade) by y
        int previousSegmentByY = 0;

        for (int i = 0; i < NDECADES; i++) {
            //a variable that stores the location of the next segment (decade) by x
            int nextSegmentByX = (getWidth() / NDECADES) * i;
            //a variable that stores the location of the next segment (decade) by y
            int nextSegmentByY = getY(entry.getRank(i));

            //draw name and rank of this name in each decade
            drawInfo(entry, color, nextSegmentByX, nextSegmentByY, i);

            //draw a graph lines, depending on the rank of the name in each decade
            drawLinesOfGraph(previousSegmentByX, previousSegmentByY, nextSegmentByX, nextSegmentByY, color);

            //remember the last points by x and from them continue to draw the graph of the next decade
            previousSegmentByX = nextSegmentByX;
            //remember the last points by y and from them we continue to draw the graph of the next decade
            previousSegmentByY = nextSegmentByY;
        }
    }

    /**
     * method that draws lines of graph
     * @param previousSegmentByX represent location of the previous segment (decade) by x
     * @param previousSegmentByY represent location of the previous segment (decade) by y
     * @param nextSegmentByX represent location of the next segment (decade) by x
     * @param nextSegmentByY represent location of the next segment (decade) by y
     * @param color of graph lines
     */
    private void drawLinesOfGraph(int previousSegmentByX, int previousSegmentByY, int nextSegmentByX, int nextSegmentByY, Color color) {
            GLine line = new GLine(previousSegmentByX, previousSegmentByY, nextSegmentByX, nextSegmentByY);
            line.setColor(color);
            add(line);
    }

    /**
     * method that determines the level by y at which information about a person's name and rank should be drawn in each decade
     *
     * @param rank of name in database
     * @return int which represent y coordination at which info should be drawn
     */
    private int getY(int rank) {
        return rank == 0 ? getHeight() - GRAPH_MARGIN_SIZE : (rank * (getHeight() - GRAPH_MARGIN_SIZE * 2)) / MAX_RANK + GRAPH_MARGIN_SIZE;
    }

    /**
     * method that define rank of name from data base.
     * if the name is not included in the 1000 most popular, so draw "*"
     *
     * @param rank of name in database
     * @return String which represent rank of name
     */
    private String rankOfName(int rank) {
        return rank == 0 ? "*" : String.valueOf(rank);
    }

    /**
     * method which create GLine with certain values
     * @param x coordinate
     * @param y coordinate
     * @param width of line
     * @param height of line
     */
    private void drawLine(double x, double y, double width, double height) {
        GLine gLine = new GLine(x, y, width, height);
        add(gLine);
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
