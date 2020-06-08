package model.panes;
/*
 *   Created by Corentin on 07/05/2020 at 00:20
 */

import core.Console;
import core.Game;

/**
 * This pane is for confirm that the player wants to quit the dungeon
 */
public class ConfirmQuit extends Pane {

    private Pane savedPane;
    private Pane finalPane;

    /**
     * Constructor of ConfirmQuit
     * @param savedPane The pane where the player come from
     * @param finalPane The pane where the player will be redirect if he wants to quit
     */
    public ConfirmQuit(Pane savedPane, Pane finalPane){
        this.savedPane = savedPane;
        this.finalPane = finalPane;
    }

    /**
     * Select if the player wants to quit or not
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        switch (c){
            case 'Y':
                Game.getGame().setPane(finalPane);
                break;
            case 'N':
                 Game.getGame().setPane(savedPane);
        }
    }

    /**
     * Propose to the player if he wants to quit or not.
     */
    @Override
    public void print() {
        Console.println("Are you sure you want to leave?");
        Console.println("Y : Yes");
        Console.println("N : No");
    }
}
