package model.equipement;
/*
 *   Created by Corentin on 05/05/2020 at 12:02
 */

/**
 * Class for the weapons. The weapons can be store in an {@link model.panes.Inventory} and use for deal damages to {@link model.entities.Foe} or {@link model.entities.Boss}.
 */
public class Weapon extends Equipment {

    private int damage;

    /**
     * Constructor of the weapon with his name and the damage deals by this weapon.
     * @param name The name of this weapon
     * @param damage The damage of this weapon
     */
    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    /**
     * Getter for get the damage of this weapon
     * @return The damage of this weapon
     */
    public int getDamage(){
        return damage;
    }

}
