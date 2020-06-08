package core;
/*
 *   Created by Corentin on 04/05/2020 at 15:31
 */

import model.panes.MainTitle;
import model.panes.Pane;

import java.util.Scanner;

/**
 * This singleton is the entry point of the game
 */
public class Game {

    private Scanner scanner;
    private Pane pane;

    /**
     * Constructor of Game. It initialise the game.
     */
    private Game(){
        init();
    }

    /**
     * Method for initialise the game.
     */
    private void init(){
        Console.setDelay(100);
        scanner = new Scanner(System.in);
        pane = new MainTitle();
        pane.print();
    }

    /**
     * Start the main loop.
     * The main loop pass the input char to the current Pane.
     * @see Pane
     */
    private void start(){
        while (pane.isRunning()){
            pane.select(Character.toUpperCase(getChar()));
        }
    }

    /**
     * Set the current pane.
     * @param pane The new pane
     */
    public void setPane(Pane pane){
        if (pane == null)
            System.exit(0);
        this.pane = pane;
        pane.print();
    }

    /**
     * Method for listen player's input.
     * @return The first char of the line.
     */
    private char getChar(){
        String line;
        do{
            Console.print(">");
            line = scanner.nextLine();
        }while (line.length() < 1);
        Console.line();
        return line.charAt(0);
    }

    private static Game game;

    /**
     * Main method
     * @param args use when start the jar file
     */
    public static void main(String[] args) {
        game = new Game();
        game.start();
    }

    /**
     * Getter for get the current Game instance
     * @return The current Game
     */
    public static Game getGame(){
        return game;
    }

}
