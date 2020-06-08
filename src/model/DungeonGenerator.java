package model;
/*
 *   Created by Corentin on 04/05/2020 at 20:34
 */

import model.entities.Boss;
import model.entities.Foe;
import model.entities.FoeType;
import model.equipement.Equipment;
import model.equipement.Key;
import model.equipement.KeyColor;
import model.equipement.Weapon;
import model.panes.Dungeon;
import model.world.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class for generate a new Dungeon.
 */
public final class DungeonGenerator {

    private final Random random;

    /**
     * Constructor of DungeonGenerator. It create a new {@link Random}.
     */
    public DungeonGenerator(){
        this.random = new Random();
    }

    /**
     * Method for generate a new random dungeon.
     * @return A dungeon to play.
     */
    public Dungeon generate(){
        Room start = new Room();
        Room end = new BossRoom(new Boss(FoeType.getRandom()));

        Room last = start;
        KeyColor color = null;

        List<KeyColor> colors = Arrays.asList(KeyColor.values());
        Collections.shuffle(colors);

        for(KeyColor keyColor : colors){
            int minRoom = random(3, 6);
            int nbRoom = random(minRoom, random(minRoom+2, minRoom+5));
            int chest = nbRoom / 2;
            for (int i = 0; i < nbRoom; i++){
                Room gen = new Room();
                Direction rc = getRandomDirection(last);
                if(i == chest)
                    gen = gen.setChest(new Key(keyColor));
                else{
                    double nb = Math.random();
                    if(nb < 0.03)
                        gen = gen.setTrap();
                    else if(nb < 0.05+0.03)
                        gen = gen.setChest(Equipment.getRandomWeapon(random));
                    else if(nb < 0.02+0.05+0.03)
                        gen = gen.setFoe(new Foe(FoeType.getRandom()));
                }

                if(color == null)
                    last.link(rc, gen);
                else {
                    last.link(rc, gen, color);
                    color = null;
                }

                if(random.nextInt(2) == 0)
                    newBranch(gen);

                last = gen;
            }
            color = keyColor;
        }

        last.link(getRandomDirection(last), end);

        return new Dungeon(start);
    }

    /**
     * Generate a number between min and max.
     * @param min The min value of the number.
     * @param max The max value of the number.
     * @return A random int.
     */
    private int random(int min, int max){
        return random.nextInt(max-min) + min;
    }

    /**
     * Return a random direction.
     * @return A random direction.
     */
    private Direction getRandomDirection(){
        switch (random(0, 4)){
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.EAST;
            case 2:
                return Direction.SOUTH;
        }
        return Direction.WEST;
    }

    /**
     * Return a random direction not use in the room in parameter.
     * @param room Room to check.
     * @return A random direction.
     */
    private Direction getRandomDirection(Room room){
        Direction rc = getRandomDirection();
        while (room.getDoor(rc) != null)
            rc = getRandomDirection();
        return rc;
    }

    /**
     * Create a new dungeon branch from the room in parameter.
     * @param room Room to start the branch.
     */
    private void newBranch(Room room){
        int length = random(2, 5);
        for(int i = 0; i < length; i++){
            Direction rd = getRandomDirection(room);
            Room nr = new Room();
            room.link(rd, nr);
            room = nr;

            double nb = Math.random();
            if(nb < 0.04)
                room = room.setTrap();
            else if(nb < 0.1+0.04)
                room = room.setChest(Equipment.getRandomWeapon(random));
            else if(nb < 0.6+0.1+0.04)
                room = room.setFoe(new Foe(FoeType.getRandom()));
        }
    }

    /**
     * Get the pre-built dungeon.
     * @return The pre-built dungeon.
     */
    public Dungeon map(){
        Room start = new Room();

        Room r1 = new Room();
        Room r2 = new Room();
        Room r3 = new Room();
        Room r4 = new Room();
        Room r5 = new Room();
        Room r6 = new Room();

        Room m1 = new FoeRoom(new Foe(FoeType.getRandom()));
        Room m2 = new FoeRoom(new Foe(FoeType.getRandom()));
        Room m3 = new FoeRoom(new Foe(FoeType.getRandom()));

        Room c1 = new ChestRoom(new Key(KeyColor.RED));
        Room c2 = new ChestRoom(new Weapon("Good Sword", 10));

        Room t1 = new TrapRoom();
        Room t2 = new TrapRoom();

        Room end = new BossRoom(new Boss(FoeType.getRandom()));

        start.link(Direction.NORTH, m1);
        m1.link(Direction.NORTH, r1);
        r1.link(Direction.NORTH, m2);
        m2.link(Direction.WEST, r2);
        r2.link(Direction.NORTH, c1);
        r2.link(Direction.WEST, t1);
        t1.link(Direction.SOUTH, c2);
        c2.link(Direction.SOUTH, r3);
        r3.link(Direction.EAST, r4);
        r4.link(Direction.EAST, m1);

        m1.link(Direction.EAST, t2, KeyColor.RED);

        t2.link(Direction.EAST, m3);
        m3.link(Direction.NORTH, r5);
        r5.link(Direction.NORTH, r6);
        r6.link(Direction.NORTH, end);

        return new Dungeon(start);
    }

}
