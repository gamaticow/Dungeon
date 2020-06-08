package model.equipement;
/*
 *   Created by Corentin on 04/05/2020 at 14:59
 */

/**
 * Class for the equipment "Key". A key can be stored in an {@link model.panes.Inventory} and got a color for open a {@link model.world.LockedDoor} with the same color.
 * @see KeyColor
 */
public class Key extends Equipment {

    private KeyColor color;

    /**
     * Constructor for the Keys
     * @param color Color of the key
     */
    public Key(KeyColor color) {
        super(color.getName()+" key");
        this.color = color;
    }

    /**
     * Getter for get the color of the key
     * @return The color of the key
     */
    public KeyColor getColor(){
        return color;
    }
}
