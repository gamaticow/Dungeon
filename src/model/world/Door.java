package model.world;
/*
 *   Created by Corentin on 04/05/2020 at 14:58
 */

/**
 * This class is for modeling the link between 2 rooms.
 * @see Room
 */
public class Door {

    protected Room room1;
    protected Room room2;

    private Direction directionFromRoom1;

    /**
     * Create a door to link 2 rooms.
     * @see Room
     * @param room1 Room to link with room2
     * @param directionFromRoom1 The direction is for the room1. The room to got the opposite direction {@link Direction#getOpposite()}
     * @param room2 Room to link with room1
     */
    public Door(Room room1, Direction directionFromRoom1, Room room2){
        this.room1 = room1;
        this.room2 = room2;
        this.directionFromRoom1 = directionFromRoom1;
    }

    /**
     * Get the direction of the door in the room pass in parameter
     * @see Direction
     * @param room Room where you want the direction of this door
     * @return The direction of the door.
     */
    public Direction getDirection(Room room){
        if(room.equals(room1))
            return directionFromRoom1;
        else if (room.equals(room2))
            return directionFromRoom1.getOpposite();

        return null;
    }

    /**
     * Return if the door is lock. Return always false in Door.
     * Override in {@link LockedDoor}
     * @see LockedDoor
     * @return Always false in the case where is a simple door. If the door is a locked door, return the state of the door.
     */
    public boolean isLocked(){
        return false;
    }

    /**
     * Get the opposite room through the door of the door pass in parameters.
     * @param room Get the opposite of this door
     * @return Opposite room, if the room in parameter isn't on this door, the result is null
     */
    public Room getOppositeRoom(Room room){
        if(room.equals(room1))
            return room2;
        else if(room.equals(room2))
            return room1;

        return null;
    }

}
