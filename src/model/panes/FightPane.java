package model.panes;
/*
 *   Created by Corentin on 22/05/2020 at 23:41
 */

import core.Console;
import core.Game;
import model.entities.Entity;
import model.world.BossRoom;
import model.world.FoeRoom;

import java.util.Random;

/**
 * The fight is in the form of a rock-paper-scissors.
 */
public class FightPane extends Pane {

    private Dungeon dungeon;
    private FoeRoom room;

    private boolean canFlee;

    /**
     * Constructor of the FightPane.
     * @param dungeon The dungeon where the player is in.
     * @param room The FoeRoom where the player is in.
     */
    public FightPane(Dungeon dungeon, FoeRoom room){
        if(room.foeIsDead())
            return;

        this.dungeon = dungeon;
        this.room = room;
        canFlee = !(room instanceof BossRoom);
        dungeon.setSubListener(this);
        room.printRoom();
        print();
    }

    /**
     * Process the rock-paper-scissors or try to flee.
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        RPS choice = RPS.getByChar(c);
        if(choice != null){
            canFlee = false;
            RPS foe = RPS.getRandom();
            Console.print(choice.toString().toLowerCase() + " - " + foe.toString().toLowerCase() + " : ");
            if(choice.isWining(foe)){
                Console.println("You win !");
                dungeon.getPlayer().attack(room.getFoe());
            }else if(foe.isWining(choice)){
                Console.println("You lose !");
                room.getFoe().attack(dungeon.getPlayer());
            }else {
                Console.println("DRAW !");
            }

            if(dungeon.getPlayer().isDead())
                return;
            else if(room.foeIsDead()){
                Console.println("You kill " + room.getFoe().getName());
                if(room instanceof BossRoom)
                    Game.getGame().setPane(new WinPane());
                else
                    dungeon.removeSubListener();
                return;
            }

            print();
        }else if(canFlee && c == 'F'){
            canFlee = false;
            if(Math.random() < Entity.FLEE_CHANCE){
                Console.println("You flee !");
                dungeon.getPlayer().goBack();
                dungeon.removeSubListener();
                return;
            }
            Console.println("You can't flee");
            print();
        }
    }

    /**
     * Print the possibility of the player.
     */
    @Override
    public void print() {
        Console.println("Your hp : " + dungeon.getPlayer().getHp() + " Foe hp : " + room.getFoe().getHp());
        for(RPS rps : RPS.values())
            Console.println(rps.keyCode +" : For "+ rps);
        Console.line();
        if(canFlee) {
            Console.println("F : To flee (33% chance to flee)");
            Console.line();
        }
    }

    /**
     * The rock paper scissors (RPS) enum.
     */
    private enum RPS {
        ROCK        ('R', 'S'),
        PAPER       ('P', 'R'),
        SCISSORS    ('S', 'P');

        public final char keyCode;
        private char winAgain;

        /**
         * Constructor of the RPS
         * @param keyCode The key to use.
         * @param winAgain The element again it win.
         */
        RPS(char keyCode, char winAgain){
            this.keyCode = keyCode;
            this.winAgain = winAgain;
        }

        /**
         * Return if the element win again an opponent
         * @param opponent The RPS opponent
         * @return If the element wins.
         */
        public boolean isWining(RPS opponent){
            return winAgain == opponent.keyCode;
        }

        /**
         * Static method for get the RPS by a char.
         * @param keyCode Char input of the player.
         * @return The RPS corresponding. Null if no RPS are corresponding.
         */
        public static RPS getByChar(char keyCode){
            for(RPS rps : values()){
                if(rps.keyCode == keyCode)
                    return rps;
            }
            return null;
        }

        /**
         * Get a random RPS. For the Foe choice.
         * @return A random RPS
         */
        public static RPS getRandom(){
            Random random = new Random();
            return values()[random.nextInt(Integer.MAX_VALUE)%values().length];
        }

    }

}
