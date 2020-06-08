package model.world;
/*
 *   Created by Corentin on 22/05/2020 at 23:38
 */

import core.Console;
import model.entities.Entity;

/**
 * FoeRoom is a room with a room with a foe for fight the player.
 */
public class FoeRoom extends Room {

    protected Entity foe;

    /**
     * Constructor of the FoeRoom.
     * @param foe The foe to put in the room.
     */
    public FoeRoom(Entity foe){
        this.foe = foe;
    }

    /**
     * Method for get if the foe is dead in the room.
     * @return If the foe is dead.
     */
    public boolean foeIsDead(){
        return foe.isDead();
    }

    /**
     * Get the foe in the room.
     * @return The foe in the room.
     */
    public Entity getFoe(){
        return foe;
    }

    /**
     * Print the content of the room if the foe is not dead. Else print the default state of the room.
     * @see Room#printRoom()
     */
    @Override
    public void printRoom() {
        if(!foeIsDead()){
            Console.println("There is a " + foe.getName() + " in this room ! You need to fight him !");
        }else {
            super.printRoom();
        }
    }
}
