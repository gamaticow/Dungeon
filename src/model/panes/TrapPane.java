package model.panes;
/*
 *   Created by Corentin on 26/05/2020 at 11:42
 */

import core.Console;
import model.world.TrapRoom;

import java.util.Random;

/**
 * TrapPane is used when a player enter in a TrapRoom.
 * @see TrapRoom
 */
public class TrapPane extends Pane {

    private static final int TIME = 6;
    private static final int LETTERS = 3;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Dungeon dungeon;
    private TrapRoom room;

    private boolean started;
    private int score;
    private int choseLetter;

    /**
     * Constructor of TrapPane. Create when a player enter in a TrapRoom with an active trap.
     * @see TrapRoom
     * @param dungeon The dungeon where the player is in.
     * @param room The TrapRoom where the player enter.
     */
    public TrapPane(Dungeon dungeon, TrapRoom room){
        if(!room.isTrapEnable())
            return;

        this.dungeon = dungeon;
        this.room = room;
        this.started = false;
        this.score = 0;
        dungeon.setSubListener(this);
        print();
    }

    /**
     * Process the game to disable the trap.
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        if(!started && c == 'S'){
            start();
        }else{
            if(ALPHABET.indexOf(c) == choseLetter+1){
                score++;
                if(score < LETTERS)
                    nextLetter();
                else {
                    if(started)
                        Console.println("You win ! Good job !\n");
                    stop();
                }
            }
        }
    }

    /**
     * Print the rules of the game to disable the trap.
     */
    @Override
    public void print() {
        Console.println("You got trapped in this room.");
        Console.println("To escape this trap, here are the rules:");
        Console.println("I'm going to propose a letter and you have to give me the next one in the alphabet.");
        Console.println("You have "+ TIME +" seconds for "+ LETTERS +" letters.");
        Console.println("S : Start the game");
    }

    /**
     * Start the game with a Thread for check if the player win the game at the end of the time.
     */
    private void start(){
        if(started) return;
        started = true;
        nextLetter();

        new Thread(() -> {
            try {
                Thread.sleep(TIME*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(score < LETTERS){
                if(started) {
                    Console.println("You lose ! You lose " + room.getTrapDamage() + " hp !\n");
                    dungeon.getPlayer().takeDamage(room.getTrapDamage());
                }
                stop();
            }
        }).start();
    }

    /**
     * Generate a random letter. And print it.
     */
    private void nextLetter(){
        Random random = new Random();
        choseLetter = random.nextInt(ALPHABET.length()-1);
        Console.println("Next letter is : " + ALPHABET.charAt(choseLetter));
    }

    /**
     * Stop the game and remove the pane in the dungeon.
     * @see Dungeon#removeSubListener()
     */
    private void stop(){
        if(!started)
            return;
        started = false;
        room.disableTrap();
        if(dungeon.getPlayer().isDead())
            return;
        dungeon.removeSubListener();
    }
}
