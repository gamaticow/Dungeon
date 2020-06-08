package model.equipement;
/*
 *   Created by Corentin on 05/05/2020 at 12:00
 */

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Equipment abstract class for weapons or keys
 * @see Key
 * @see Weapon
 */
public abstract class Equipment {

    private static int ID = 0;

    private String name;
    private int id;

    /**
     * Constructor of Equipment
     * @param name The name of the equipment
     */
    public Equipment(String name){
        this.name = name;
        id = ID++;
    }

    /**
     * Getter for the equipment's name
     * @return The name of the equipment
     */
    public String getName(){
        return name;
    }

    /**
     * Override of the equals method. For test if 2 object are similar.
     * @param obj The object to compare
     * @return If the object is similar
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Equipment){
            return id == ((Equipment) obj).id;
        }
        return false;
    }

    /**
     * Return a random equipment.
     * @param random The random object.
     * @return A random equipment.
     */
    public static Weapon getRandomWeapon(Random random) {
        List<String> names = Arrays.asList("King Arthur sword", "Skeleton bow", "Dwarf bone", "Club", "Rifle", "Whip", "Dagger", "Shuriken", "Small sword");
        return new Weapon(names.get(random.nextInt(names.size())), random.nextInt(15)+6);
    }
}
