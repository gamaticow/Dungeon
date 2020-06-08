package model.panes;
/*
 *   Created by Corentin on 29/05/2020 at 11:30
 */

import core.Console;
import core.Game;

/**
 * GameOverPane is used when the player dies.
 */
public class GameOverPane extends Pane {

    /**
     * If the enter char is Q, return to the main title for start a new dungeon.
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        if(c == 'Q')
            Game.getGame().setPane(new MainTitle());
    }

    /**
     * Print the Game Over. And how to quit.
     */
    @Override
    public void print() {
        Console.println("\n" +
                "   _____                         ____                   _ \n" +
                "  / ____|                       / __ \\                 | |\n" +
                " | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __  | |\n" +
                " | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| | |\n" +
                " | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |    |_|\n" +
                "  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|    (_)\n" +
                "                                                          \n" +
                "                                                          \n");
        Console.println("Q : Quit");
    }
}
