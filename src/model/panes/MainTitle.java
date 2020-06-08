package model.panes;
/*
 *   Created by Corentin on 04/05/2020 at 20:48
 */

import core.Console;
import core.Game;
import model.DungeonGenerator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Pane for the main title. This pane is open at the start of the game or when the player quit a dungeon.
 */
public class MainTitle extends Pane {

    /**
     * Process player inputs on main title
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        switch (c){
            case 'Q':
                running = false;
                break;
            case 'S':
                Game.getGame().setPane(new DungeonGenerator().generate());
                break;
            case 'M':
                Game.getGame().setPane(new DungeonGenerator().map());
                break;
            case 'O':
                openSoluce();
                print();
        }
    }

    /**
     * Print main title menu
     */
    @Override
    public void print(){
        Console.println("  _____                                     \n" +
                " |  __ \\                                    \n" +
                " | |  | |_   _ _ __   __ _  ___  ___  _ __  \n" +
                " | |  | | | | | '_ \\ / _` |/ _ \\/ _ \\| '_ \\ \n" +
                " | |__| | |_| | | | | (_| |  __/ (_) | | | |\n" +
                " |_____/ \\__,_|_| |_|\\__, |\\___|\\___/|_| |_|\n" +
                "                      __/ |                 \n" +
                "                     |___/                  ");
        Console.line();
        Console.println("S : Start the game with a random dungeon");
        Console.println("M : Start the game on the pre-built dungeon");
        Console.println("O : Open the soluce of the pre-built dungeon");
        Console.println("Q : Quit the game");
    }

    /**
     * Open the PDF of the prebuild dungeon if the player's device is compatible.
     */
    private void openSoluce(){
        if(Desktop.isDesktopSupported()){
            try{

                ClassLoader classLoader = getClass().getClassLoader();

                URL resource = classLoader.getResource("dungeon.pdf");
                if (resource == null) {
                    throw new IllegalArgumentException("file is not found!");
                } else {
                    Path dungeon_pdf = Files.createTempFile("DungeonMap", ".pdf");
                    try (InputStream is = getClass().getClassLoader().getResourceAsStream("dungeon.pdf")) {
                        Files.copy(is, dungeon_pdf, StandardCopyOption.REPLACE_EXISTING);
                    }
                    File dungeon = dungeon_pdf.toFile();
                    Desktop.getDesktop().open(dungeon_pdf.toFile());
                    dungeon.deleteOnExit();
                }
            } catch (IOException e){
                Console.println("PDF missing");
            }
        }else{
            Console.println("Your device can't read PDF");
        }
    }
}
