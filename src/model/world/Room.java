package model.world;
/*
 *   Created by Corentin on 04/05/2020 at 14:55
 */

import core.Console;
import model.DungeonGenerator;
import model.entities.Foe;
import model.equipement.Equipment;
import model.equipement.Key;
import model.equipement.KeyColor;
import model.exceptions.KeyNotCompatibleException;
import model.panes.ConfirmUseKey;
import model.panes.Dungeon;
import model.panes.FightPane;
import model.panes.TrapPane;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the room management of the system. These rooms are link thanks to {@link Door}.
 */
public class Room {

    private List<Door> doors;

    /**
     * Constructor of Room class, It just initialise the list for register the doors.
     */
    public Room(){
        doors = new ArrayList<>();
    }

    /**
     * This method is for link a door with a other room. The door is create in the {@link Direction} in parameter.
     * And the room in parameter will got a door for go back in the opposite direction ({@link Direction#getOpposite()}).
     * @see Door
     * @param direction The door is create in this direction
     * @param room This room will got a door in the opposite direction
     */
    public void link(Direction direction, Room room){
        link(new Door(this, direction, room), room);
    }

    /**
     * This method is the same like {@link #link(Direction, Room)}. But the door will be lock with the lock color ({@link KeyColor}) pass in parameter.
     * @see LockedDoor
     * @see KeyColor
     * @see Key
     * @param direction The door is create in this direction
     * @param room This room will got a door in the opposite direction
     * @param keyColor The color of the lock on the door
     */
    public void link(Direction direction, Room room, KeyColor keyColor){
        link(new LockedDoor(this, direction, room, keyColor), room);
    }

    /**
     * Private method for link two room with one {@link Door}/{@link LockedDoor}.
     * The method is call by {@link #link(Direction, Room)} or {@link #link(Direction, Room, KeyColor)}
     * @param door The door to add to rooms
     * @param room This room will got a door in the opposite direction
     */
    private void link(Door door, Room room){
        doors.add(door);
        room.doors.add(door);
    }

    /**
     * This method is for forward the player in the {@link Direction} pass in parameter.
     * If there is no door in this room in this {@link Direction}, this method do nothing.
     * If the door is lock ({@link LockedDoor}) and the {@link model.entities.Player} got the key, the player can chose if he wants to use his {@link Key} on this {@link LockedDoor} for unlock it.
     *
     * @param dungeonInstance Dungeon where the player is in.
     * @param direction Direction where the player wants to go.
     */
    public void forward(Dungeon dungeonInstance, Direction direction){
        Door door = getDoor(direction);

        if(door != null) {
            //Test if the door is a locked door and if the door is lock
            if(door.isLocked()){
                LockedDoor d = (LockedDoor) door;
                Key key = dungeonInstance.getPlayer().inventory.getKey(d.getLockColor());
                if(key != null) {
                    try {
                        new ConfirmUseKey(dungeonInstance, d, key);
                    } catch (KeyNotCompatibleException e) {
                        e.printStackTrace();
                    }
                }else {
                    Console.println("This door seems to be locked. The lock is " + d.getLockColor().getName().toLowerCase() + ".");
                }
            }else{
                Room newRoom = door.getOppositeRoom(this);
                dungeonInstance.getPlayer().move(newRoom);
                if(newRoom instanceof FoeRoom){
                    new FightPane(dungeonInstance, (FoeRoom) newRoom);
                }else if(newRoom instanceof TrapRoom){
                    new TrapPane(dungeonInstance, (TrapRoom) newRoom);
                }
            }
        }else
            Console.println("There is no door on this wall");
    }

    /**
     * This function is for get the {@link Door} in this room on the {@link Direction} in parameter.
     * @param direction Direction where you want the door.
     * @return The {@link Door} if exists in this {@link Direction}. If there is no door in this {@link Direction} return null
     */
    public Door getDoor(Direction direction){
        for(Door d : doors){
            if(d.getDirection(this) == direction)
                return d;
        }
        return null;
    }

    /**
     * Print the direction where there is a {@link Door} in the room to the player console.
     */
    public void printRoom(){
        boolean n = false;
        boolean e = false;
        boolean s = false;
        boolean w = false;
        for(Door door : doors){
            switch (door.getDirection(this)){
                case NORTH:
                    n = true;
                    break;
                case EAST:
                    e = true;
                    break;
                case SOUTH:
                    s = true;
                    break;
                case WEST:
                    w = true;
                    break;
            }
        }

        if(n)
            Console.println(Direction.NORTH.getKeyCode() + " : To take the door on " + Direction.NORTH);
        if(e)
            Console.println(Direction.EAST.getKeyCode() + " : To take the door on " + Direction.EAST);
        if(s)
            Console.println(Direction.SOUTH.getKeyCode() + " : To take the door on " + Direction.SOUTH);
        if(w)
            Console.println(Direction.WEST.getKeyCode() + " : To take the door on " + Direction.WEST);
    }

    /**
     * Convert this room to a {@link ChestRoom}.
     * Used by random generator ({@link DungeonGenerator#generate()})
     * @param inChest The item in the chest.
     * @return The new room to use. If the old rooms was linked, this room got same links.
     */
    public ChestRoom setChest(Equipment inChest){
        Room room = new ChestRoom(inChest);
        swapDoors(room);
        room.doors = doors;
        return (ChestRoom) room;
    }

    /**
     * Convert this room to a {@link FoeRoom}.
     * Used by random generator ({@link DungeonGenerator#generate()})
     * @param foe The foe in the room.
     * @return The new room to use. If the old rooms was linked, this room got same links.
     */
    public FoeRoom setFoe(Foe foe){
        Room room = new FoeRoom(foe);
        swapDoors(room);
        room.doors = doors;
        return (FoeRoom) room;
    }

    /**
     * Convert this room to a {@link TrapRoom}.
     * Used by random generator ({@link DungeonGenerator#generate()})
     * @return The new room to use. If the old rooms was linked, this room got same links.
     */
    public Room setTrap() {
        Room room = new TrapRoom();
        swapDoors(room);
        room.doors = doors;
        return (TrapRoom) room;
    }

    /**
     * Convert the links to a new Room.
     * @param newRoom The room to put the links
     */
    private void swapDoors(Room newRoom){
        for (Door door : doors){
            if(door.room1 == this)
                door.room1 = newRoom;
            else if(door.room2 == this)
                door.room2 = newRoom;
        }
    }
}
