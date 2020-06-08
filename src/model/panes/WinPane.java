package model.panes;
/*
 *   Created by Corentin on 07/06/2020 at 15:47
 */

import core.Console;
import core.Game;

public class WinPane extends Pane {
    @Override
    public void select(char c) {
        if(c == 'Q')
            Game.getGame().setPane(new MainTitle());
    }

    @Override
    public void print() {
        Console.println("\n" +
                " __     __                    _         _ \n" +
                " \\ \\   / /                   (_)       | |\n" +
                "  \\ \\_/ /__  _   _  __      ___ _ __   | |\n" +
                "   \\   / _ \\| | | | \\ \\ /\\ / / | '_ \\  | |\n" +
                "    | | (_) | |_| |  \\ V  V /| | | | | |_|\n" +
                "    |_|\\___/ \\__,_|   \\_/\\_/ |_|_| |_| (_)\n" +
                "                                          \n" +
                "                                          \n");
        Console.line();
        Console.println("Q : Return to main menu");
    }
}
