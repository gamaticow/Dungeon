package model.panes;
/*
 *   Created by Corentin on 05/05/2020 at 19:01
 */

import core.Console;
import model.equipement.Key;
import model.exceptions.KeyNotCompatibleException;
import model.world.ChestRoom;
import model.world.FoeRoom;
import model.world.LockedDoor;
import model.world.TrapRoom;

/**
 * This pane is use for confirm if the player really wants to use his key on a door.
 */
public class ConfirmUseKey extends Pane {

    private Dungeon dungeon;

    private LockedDoor door;
    private Key key;

    /**
     * Constructor for Confirm use key. Put as sub pane in the {@link Dungeon#setSubListener(Pane)}
     * @param dungeon Dungeon where the player is
     * @param door Door to open
     * @param key Key to use
     * @throws KeyNotCompatibleException Exception throws if the key and the door haven't the same color.
     */
    public ConfirmUseKey(Dungeon dungeon, LockedDoor door, Key key) throws KeyNotCompatibleException {
        if(door.getLockColor() != key.getColor())
            throw new KeyNotCompatibleException(door.getLockColor(), key.getColor());

        this.door = door;
        this.key = key;

        this.dungeon = dungeon;
        dungeon.setSubListener(this);
        print();
    }

    /**
     * Process if the player want to open this door.
     * <ul>
     *     <li>If the player wants to open the door
     *     <ol>
     *         <li>Open the door</li>
     *         <li>Forward the player to the open room</li>
     *     </ol>
     *     </li>
     *     <li>If the player don't wants to open the door. The key is returned to his inventory.</li>
     * </ul>
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        if(c == 'Y'){
            door.useKey(key);
            dungeon.getPlayer().getRoom().forward(dungeon, door.getDirection(dungeon.getPlayer().getRoom()));
            if(!(dungeon.getPlayer().getRoom() instanceof TrapRoom || dungeon.getPlayer().getRoom() instanceof ChestRoom || dungeon.getPlayer().getRoom() instanceof FoeRoom)){
                dungeon.removeSubListener();
            }
        }else if(c == 'N'){
            dungeon.getPlayer().inventory.addItem(key);
            dungeon.removeSubListener();
        }
    }

    /**
     * Print the menu for confirm if the player really wants to use his key on this door.
     */
    @Override
    public void print() {
        Console.println("You want use your " + key.getColor().getName().toLowerCase() + " key for open this door ?");
        Console.println("/!\\ Your " + key.getColor().getName().toLowerCase() + " key will be destroy if you use it !");
        Console.line();
        Console.println("Y : Yes");
        Console.println("N : No");
    }


}
