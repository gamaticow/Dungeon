package model.entities;
/*
 *   Created by Corentin on 11/05/2020 at 13:45
 */

import model.world.BossRoom;

/**
 * The boss class for the BossRoom
 * @see BossRoom
 */
public class Boss extends Entity {

    private int damage;

    /**
     * Constructor of the boss. Use the characteristics of the {@link FoeType}
     * @param foeType The foetype of the boss
     */
    public Boss(FoeType foeType){
        super("huge " + foeType.getName(), foeType.getBossHp());
        damage = foeType.getBossDamage();
    }

    /**
     * Getter for get the damage of the boss
     * @return The damage of the boss
     */
    @Override
    public int getDamage() {
        return damage;
    }
}
