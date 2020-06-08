package model.entities;
/*
 *   Created by Corentin on 11/05/2020 at 13:42
 */

/**
 * Foe class for place in {@link model.world.FoeRoom}.
 */
public class Foe extends Entity {

    /**
     * Constructor of Foe with a FoeType
     * @see FoeType
     * @param type The type of the foe
     */
    public Foe(FoeType type) {
        super(type.getName());
    }

    /**
     * Get the damage of the foe.
     * @return Always 1 damage (but deal 2 if Foe has a critical strike ({@link Entity#attack(Entity)}))
     */
    @Override
    public int getDamage() {
        return 1;
    }
}
