package model.entities;
/*
 *   Created by Corentin on 11/05/2020 at 13:46
 */

import java.util.Random;

/**
 * Enum for list all possible foe.
 */
public enum FoeType {

    ZOMBIE      ("zombie",    50,   3),
    SPIDER      ("spider",    40,   3),
    SKELETON    ("skeleton",  30,   4),
    DWARF       ("dwarf",     70,   2);

    private String name;
    private int bossHp;
    private int bossDamage;

    /**
     * Constructor of the FoeType enum
     * @see Boss
     * @param name The name of the foe
     * @param bossHp The hp if the foe is a boss
     * @param bossDamage The damage if the foe is a boss
     */
    FoeType(String name, int bossHp, int bossDamage){
        this.name = name;
        this.bossHp = bossHp;
        this.bossDamage = bossDamage;
    }

    /**
     * Get the name of the entity
     * @return The name of the entity (in the constructor)
     */
    public String getName(){
        return name;
    }

    /**
     * Get the hp if the entity is a boss
     * @return The hp amount of the entity boss (in the constructor)
     */
    public int getBossHp(){
        return bossHp;
    }

    /**
     * Get the damage if the entity is a boss
     * @return The damage of the entity boss (in the constructor)
     */
    public int getBossDamage(){
        return bossDamage;
    }

    /**
     * This static method is for get a random FoeType
     * @return A random FoeType
     */
    public static FoeType getRandom(){
        Random random = new Random();
        return values()[random.nextInt(Integer.MAX_VALUE)%values().length];
    }

}
