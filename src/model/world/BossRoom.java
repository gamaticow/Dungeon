package model.world;
/*
 *   Created by Corentin on 22/05/2020 at 23:39
 */

import core.Console;
import model.entities.Boss;

/**
 * This class is here to make the fight against a boss more epic.
 */
public class BossRoom extends FoeRoom {
    public BossRoom(Boss foe) {
        super(foe);
    }

    @Override
    public void printRoom() {
        if(!foeIsDead()){
            Console.println("This is the last room until the end of the dungeon !");
            Console.println("But there is a " + foe.getName() + " in this room ! You need to beat him for exit the dungeon !");
            Console.println("And this time you can't flee. Good luck !");
        }
    }
}
