package model.entities;
/*
 *   Created by Corentin on 04/05/2020 at 15:30
 */

import core.Console;
import core.Game;
import model.panes.Dungeon;
import model.panes.GameOverPane;
import model.panes.Inventory;
import model.world.Room;

/**
 * The player entity class.
 */
public class Player extends Entity {

    private Room position;
    private Room last;
    public Inventory inventory;

    /**
     * Constructor of the Player.
     * @param dungeon The dungeon where the player is in.
     * @param position The room where the player start in the dungeon.
     */
    public Player(Dungeon dungeon, Room position){
        super("player");
        this.inventory = new Inventory(dungeon);
        this.position = position;
    }

    /**
     * Change room where the player is in
     * @param room The new room
     */
    public void move(Room room){
        last = position;
        position = room;
    }

    /**
     * Go back the the previous room. Used if the player want to flee.
     * @see model.panes.FightPane
     */
    public void goBack(){
        position = last;
    }

    /**
     * Get the actual position of the player
     * @return The room where the player is in
     */
    public Room getRoom(){
        return position;
    }

    /**
     * Print what the player can do.
     */
    public void printMenu(){
        position.printRoom();
        Console.line();
        Console.println("I : Open your inventory");
        Console.println("Q : Quit the current dungeon");
    }

    /**
     * Get the damage of the weapon equipped
     * @see model.equipement.Weapon
     * @return The damage of the equipped weapon.
     */
    @Override
    public int getDamage() {
        return inventory.getEquippedWeapon().getDamage();
    }

    /**
     * Deal damage to the player. If the player is dead, print the {@link GameOverPane}.
     * @param damage The damage to deal
     */
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if(isDead())
            Game.getGame().setPane(new GameOverPane());
    }
}
