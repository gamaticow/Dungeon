package model.entities;
/*
 *   Created by Corentin on 11/05/2020 at 13:32
 */

import core.Console;

public abstract class Entity {

    /**
     * Default HP amount of an entity
     */
    private static final int DEFAULT_HP = 20;

    /**
     * Critical chance for all entities
     */
    private static final float CRITICAL_CHANCE = 0.1f;

    /**
     * Critical change for player entity
     */
    private static final float PLAYER_CRITICAL_CHANCE = 0.4f;

    /**
     * Chance to flee a fight for a player
     */
    public static final float FLEE_CHANCE = 0.33f;

    private String name;
    private int hp;

    /**
     * Constructor of Entity with his name. This entity will got the default HP amount.
     * @param name The name of the entity
     */
    public Entity(String name){
        this(name, DEFAULT_HP);
    }

    /**
     * Constructor of Entity with his name and his hp.
     * @param name The name of the entity
     * @param hp The hp of the entity
     */
    public Entity(String name, int hp){
        this.name = name;
        this.hp = hp;
    }

    /**
     * Attack an entity. Use his damage ({@link #getDamage()}) and double with the crit chance.
     * @param entity The entity to attack
     */
    public void attack(Entity entity){
        if(isDead() || entity.isDead())
            return;

        int damage = getDamage();

        //CRIT
        float crit = CRITICAL_CHANCE;
        if(this instanceof Player)
            crit = PLAYER_CRITICAL_CHANCE;
        if(Math.random() < crit) {
            Console.print(this.name + " had a critical hit ! ");
            damage *= 2;
        }

        if(entity.hp - damage > 0)
            Console.println(this.name + " deals " + damage + " hp to " + entity.name);
        else
            Console.println(this.name + " kills " + entity.name);

        entity.takeDamage(damage);
    }

    /**
     * Deal damage to the entity.
     * @see model.panes.TrapPane
     * @see model.panes.FightPane
     * @param damage The damage to deal
     */
    public void takeDamage(int damage){
        hp -= damage;
    }

    /**
     * Return if the current entity is dead.
     * @return <ul>
     *     <li>True : The entity is dead</li>
     *     <li>False : The entity is alive</li>
     * </ul>
     */
    public boolean isDead(){
        return hp <= 0;
    }

    /**
     * Get the current HP of the entity. If his hp is negative return 0.
     * @return The hp of the current entity.
     */
    public int getHp(){
        return Math.max(hp, 0);
    }

    /**
     * Abstract method to get the damage of the entity.
     * @return The damage of the current entity
     */
    public abstract int getDamage();

    /**
     * Get the name of the current entity
     * @return The name of the current entity
     */
    public String getName(){
        return name;
    }

}
