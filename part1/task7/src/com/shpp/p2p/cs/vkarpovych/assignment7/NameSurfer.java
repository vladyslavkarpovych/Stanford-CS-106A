package com.shpp.p2p.cs.vkarpovych.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    //create a button with an inscription "Graph"
    private JButton graphButton = new JButton("Graph");
    //create a button with an inscription "Clear"
    private JButton clearButton = new JButton("Clear");
    //create a label with an inscription "Name: "
    private JLabel nameLabel = new JLabel("Name: ");
    //create text field with size which equals to 20 columns
    private JTextField textField = new JTextField(SIZE_OF_TEXT_FIELD);
    //reference to the class object which responsible for drawing the graph
    private NameSurferGraph graph = new NameSurferGraph();
    //reference to the class which takes file path and read lined from it
    private NameSurferDataBase nameSurferDataBase;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        //add all created above objects into app window
        add(nameLabel, NORTH);
        add(textField, NORTH);
        textField.setActionCommand("EnterPressed");
        textField.addActionListener(this);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        add(graph);
        addActionListeners();

        try {
            nameSurferDataBase = new NameSurferDataBase("src/" + NAMES_DATA_FILE);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {

        //variable that saving text, which was input in text field by user
        String contentOfTextField = textField.getText();

        if (e.getSource().equals(clearButton)) {
            graph.clear();
            graph.update();
        }

        if (e.getSource().equals(graphButton) || e.getActionCommand().equals("EnterPressed")) {
            NameSurferEntry entry = nameSurferDataBase.findEntry(contentOfTextField);
            if (!(entry == null)) {
                graph.addEntry(entry);
                graph.update();
            }
        }
    }
}
