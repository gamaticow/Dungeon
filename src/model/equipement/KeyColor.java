package model.equipement;
/*
 *   Created by Corentin on 04/05/2020 at 15:00
 */

/**
 * List of the color possible for {@link Key} and {@link model.world.LockedDoor}.
 */
public enum KeyColor {

    RED     ("Red"),
    GREEN   ("Green");

    private String name;

    /**
     * Constructor for the KeyColor
     * @param name The display name of this color for the player in the dungeon
     */
    KeyColor(String name){
        this.name = name;
    }

    /**
     * Getter for the display name of the color
     * @return The display name of the color
     */
    public String getName(){
        return name;
    }
}
