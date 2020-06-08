package model.panes;
/*
 *   Created by Corentin on 04/05/2020 at 20:49
 */

import core.Game;

/**
 * Abstract class for the pane who print with {@link #print()} and process inputs with {@link #select(char)}
 */
public abstract class Pane {

    protected boolean running = true;

    /**
     * Use by {@link Game#start()} for process player's input
     * @param c Char to process
     */
    public abstract void select(char c);

    /**
     * Return if the pane is running or not.
     * Use for stop the main loop.
     * @see Game
     * @return a boolean
     * <ul>
     *     <li>true : The pane is running</li>
     *     <li>false: The pane is not running</li>
     * </ul>
     */
    public boolean isRunning(){
        return running;
    }

    /**
     * Abstract method for print the pane in the user's console.
     */
    public abstract void print();
}
