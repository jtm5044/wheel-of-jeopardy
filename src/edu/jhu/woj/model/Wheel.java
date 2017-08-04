package edu.jhu.woj.model;

import java.util.*;

/**
 * The model for the Wheel.
 *
 * Separate lists are maintained for default wheel sectors and question board sectors.
 * The two lists are shuffled together to randomize the sector positions on the wheel.
 *
 * Created by jeremy on 6/22/17.
 */
public class Wheel {

    // Constant values
    private static final int NUM_QUESTION_BOARD_SECTORS = 6;
    public static final String WHEEL_SECTOR_LOSE_TURN = "Lose Turn";
    public static final String WHEEL_SECTOR_FREE_TURN = "Free Turn";
    public static final String WHEEL_SECTOR_BANKRUPT = "Bankrupt";
    public static final String WHEEL_SECTOR_PLAYERS_CHOICE = "Player's Choice";
    public static final String WHEEL_SECTOR_OPP_CHOICE = "Opponent's Choice";
    public static final String WHEEL_SECTOR_SPIN_AGAIN = "Spin Again";

    private ArrayList<String> questionBoardSectors = new ArrayList<String>();
    private ArrayList<String> defaultWheelSectors = new ArrayList<String>();
    private ArrayList<String> wheelSectors = new ArrayList<String>();

    private int currentlySelectedWheelIndex = -1;
    private String currentlySelectedWheelSector = "";

    /**
     * Constructs a Wheel object and randomizes the wheel sectors.
     * IllegalArgumentException if question board has more or less than 6 elements or the elements are not unique
     * @param questionBoardSectors the 6 question board sectors
     */
    public Wheel(List<String> questionBoardSectors)
    {
        checkQuestionBoardInput(questionBoardSectors);

        // Initialize question board sectors from constructor input
        this.questionBoardSectors.addAll(questionBoardSectors);

        // Initialize default sections of the wheel
        this.defaultWheelSectors.add(WHEEL_SECTOR_BANKRUPT);
        this.defaultWheelSectors.add(WHEEL_SECTOR_FREE_TURN);
        this.defaultWheelSectors.add(WHEEL_SECTOR_LOSE_TURN);
        this.defaultWheelSectors.add(WHEEL_SECTOR_OPP_CHOICE);
        this.defaultWheelSectors.add(WHEEL_SECTOR_PLAYERS_CHOICE);
        this.defaultWheelSectors.add(WHEEL_SECTOR_SPIN_AGAIN);

        // Then initialize and randomize the wheel
        randomizeWheel();
    }

    /**
     * Returns the currently selected wheel index or -1 if there is no currently selected wheel index.
     * @return the currently selected wheel index
     */
    public int getCurrentlySelectedWheelIndex()
    {
        return currentlySelectedWheelIndex;
    }

    /**
     * Sets the currently selected wheel index. Updates the currently selected wheel sector.
     * IndexOutOfBoundsException if index is outside of the wheel sector boundaries.
     * @param index the index of the wheel to select
     */
    public void setCurrentlySelectedWheelIndex(int index)
    {
        if(index >= 0 && index < wheelSectors.size()) {
            currentlySelectedWheelIndex = index;
            currentlySelectedWheelSector = wheelSectors.get(index);
        }else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the currently selected wheel sector or a blank string if there is no currently selected wheel sector.
     * @return the currently selected wheel sector
     */
    public String getCurrentlySelectedWheelSector()
    {
        return currentlySelectedWheelSector;
    }

    /**
     * Sets the currently selected wheel sector. Updates the currently selected wheel index.
     * NoSuchElementException if sector is not found
     * @param sector the sector of the wheel to select
     */
    public void setCurrentlySelectedWheelSector(String sector)
    {
        if(wheelSectors.contains(sector))
        {
            currentlySelectedWheelIndex = wheelSectors.indexOf(sector);
            currentlySelectedWheelSector = wheelSectors.get(currentlySelectedWheelIndex);
        }else
        {
            throw new NoSuchElementException("Sector '" + sector + "' not found.");
        }
    }

    /**
     * Sets the question board sectors. Must be exactly 6 question board sectors.
     * IllegalArgumentException if there are more or less than 6 question board sectors.
     * @param questionBoardSectors the question board sectors to select
     */
    public void setQuestionBoardSectors(List<String> questionBoardSectors)
    {

        checkQuestionBoardInput(questionBoardSectors);

        this.questionBoardSectors.clear();
        this.questionBoardSectors.addAll(questionBoardSectors);
        randomizeWheel();
    }

    /**
     * Clears the wheel, adds the sectors, then randomizes the sectors
     */
    private void randomizeWheel() {

        // Clear the wheel and add the sectors
        this.wheelSectors.clear();
        this.wheelSectors.addAll(this.questionBoardSectors);
        this.wheelSectors.addAll(this.defaultWheelSectors);

        // Then shuffle everything up
        Collections.shuffle(wheelSectors, new Random());
    }

    /**
     * Returns a copy of the wheel sectors in order
     * @return a copy of the wheel sectors in order
     */
    public String[] getWheel()
    {
        String[] ws = new String[wheelSectors.size()];
        return wheelSectors.toArray(ws);
    }

    /**
     * Returns the wheel sectors in order in string format for printing
     * @return the wheel sectors in order in string format for printing
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < wheelSectors.size(); i++)
        {
            sb.append(wheelSectors.get(i)).append(", ");
        }
        sb.delete(sb.lastIndexOf(","),sb.length());
        return sb.toString();
    }

    /**
     * Ensures the question board input is valid
     * IllegalArgumentException if there are more or less than NUM_QUESTION_BOARD_SECTORS elements or if the elements are not unique
     * @param questionBoardSectors
     * @return true if question board input is valid, false otherwise
     */
    private boolean checkQuestionBoardInput(List<String> questionBoardSectors)
    {
        if(questionBoardSectors.size() != NUM_QUESTION_BOARD_SECTORS)
        {
            throw new IllegalArgumentException("Question board must have exactly " + NUM_QUESTION_BOARD_SECTORS + " unique elements");
        }

        if(!checkForUniqueElements(questionBoardSectors))
        {
            throw new IllegalArgumentException("Question board elements must be unique");
        }

        return true;
    }

    /**
     * Helper method to check a string list and ensure all elements are unique
     * @param l
     * @return true if elements are unique, false otherwise.
     */
    private boolean checkForUniqueElements(List<String> l)
    {
        for(int i = 0; i < l.size(); i++) {
            if(Collections.frequency(l, l.get(i)) > 1)
            {
                return false;
            }
        }
        return true;
    }
}
