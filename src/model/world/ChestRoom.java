package model.world;
/*
 *   Created by Corentin on 05/05/2020 at 20:03
 */

import core.Console;
import model.entities.Player;
import model.equipement.Equipment;

/**
 * This is a special room with a chest.
 * Inherit from {@link Room}
 */
public class ChestRoom extends Room {

    private boolean chestOpen = false;
    private Equipment itemInChest;

    /**
     * Same like {@link Room#Room()}. With a {@link Equipment} in the chest. The chest is close by default.
     * @param itemInChest The item n the chest.
     */
    public ChestRoom(Equipment itemInChest){
        super();
        this.itemInChest = itemInChest;
    }

    /**
     * Open the chest and put the chest in the {@link model.panes.Inventory} of the {@link Player} pass in parameter.
     * When the chest is open it can't be open again.
     * @param player Player who get the content of the chest
     */
    public void openChest(Player player){
        if(player.getRoom() == this && !chestOpen){
            chestOpen = true;
            player.inventory.addItem(itemInChest);
        }
    }

    /**
     * Override {@link Room#printRoom()} for add some information like chest state.
     * @see Room#printRoom()
     */
    @Override
    public void printRoom() {
        if(!chestOpen){
            Console.println("There is a chest in this room.");
        }else {
            Console.println("I already open the chest in this room.");
        }
        Console.line();
        super.printRoom();
        if(!chestOpen)
            Console.println("O : Open the chest");
    }
}
