package model.panes;
/*
 *   Created by Corentin on 04/05/2020 at 16:29
 */

import core.Game;
import model.entities.Player;
import model.world.ChestRoom;
import model.world.Direction;
import model.world.Room;

/**
 * This is the main pane for play on the game.
 */
public class Dungeon extends Pane {

    private Player player;
    private Pane subListener;

    /**
     * Constructor of dungeon. It create player in the room past in parameter.
     * @param startRoom The room where the player will spawn.
     */
    public Dungeon(Room startRoom){
        player = new Player(this, startRoom);
    }

    /**
     * Get the player in the dungeon
     * @return The player in the dungeon
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Set the sub pane for process inputs.
     * @see Inventory
     * @see ConfirmUseKey
     * @param subListener The sub pane to display
     */
    public void setSubListener(Pane subListener){
        this.subListener = subListener;
    }

    /**
     * Remove the sub pane to process input in this object.
     * Print this object content too.
     */
    public void removeSubListener(){
        subListener = null;
        print();
    }

    /**
     * Process input in the game.
     * If a sub pane is enable, give process to the sub pane.
     * @param c Char to process
     */
    @Override
    public void select(char c){
        if(subListener != null){
            subListener.select(c);
            return;
        }

        Direction cardinality = Direction.getCardinalityByChar(c);
        if(cardinality != null){
            player.getRoom().forward(this, cardinality);
            if(subListener != null)
                return;
        }

        switch (c){
            case 'Q':
                Pane pane = new ConfirmQuit(this, new MainTitle());
                Game.getGame().setPane(pane);
                return;
            case 'I':
                player.inventory.openInventory();
                return;
            case 'O':
                if(player.getRoom() instanceof ChestRoom){
                    ((ChestRoom) player.getRoom()).openChest(player);
                    if(subListener != null)
                        return;
                }
                break;
        }

        print();

    }

    /**
     * Print the menu for play.
     */
    @Override
    public void print() {
        player.printMenu();
    }

}
