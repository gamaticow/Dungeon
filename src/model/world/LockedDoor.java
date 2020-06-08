package model.world;
/*
 *   Created by Corentin on 04/05/2020 at 15:02
 */

import model.equipement.Key;
import model.equipement.KeyColor;

/**
 * This is a special door inherit {@link Door}.
 * This door is lock with a {@link KeyColor}.
 */
public class LockedDoor extends Door {

    private KeyColor keyForOpen;
    private boolean locked = true;

    /**
     * Same like {@link Door#Door(Room, Direction, Room)}. With a {@link KeyColor} for unlock the door. A locked door is close by default.
     * @param room1 Room to link with room2
     * @param directionFromRoom1 The direction is for the room1. The room to got the opposite direction {@link Direction#getOpposite()}
     * @param room2 Room to link with room1
     * @param keyForOpen The color for unlock the door
     */
    public LockedDoor(Room room1, Direction directionFromRoom1, Room room2, KeyColor keyForOpen) {
        super(room1, directionFromRoom1, room2);
        this.keyForOpen = keyForOpen;
    }

    /**
     *  Use a key on a door. If the key got the good color, the door will be unlock.
     * @see Key
     * @param key Key for unlock the door.
     */
    public void useKey(Key key){
        if(key.getColor() == keyForOpen){
            locked = false;
        }
    }

    /**
     * The the lock color of the door
     * @return {@link KeyColor}
     */
    public KeyColor getLockColor(){
        return keyForOpen;
    }

    /**
     * Return the state of the door.
     * <ul>
     *     <li>true if close</li>
     *     <li>false if open</li>
     * </ul>
     * @return The state of the door
     */
    @Override
    public boolean isLocked() {
        return locked;
    }
}
