package model.panes;
/*
 *   Created by Corentin on 05/05/2020 at 11:59
 */

import core.Console;
import model.equipement.Equipment;
import model.equipement.Key;
import model.equipement.KeyColor;
import model.equipement.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory pane for process player's interaction with his inventory.
 * Inventory Store the player's equipment.
 */
public class Inventory extends Pane {

    private static final int MAX_WEAPONS = 5;

    private final Dungeon dungeon;

    private final List<Weapon> weapons;
    private Weapon equipped;
    private boolean dropMode = false;
    private boolean quitAfterDrop = false;
    private Equipment itemToAdd;

    private final List<Key> keys;

    /**
     * Constructor for inventory. It initialize Inventory and put a basic sword in.
     * @param dungeon Dungeon in which the player is
     */
    public Inventory(Dungeon dungeon){
        this.dungeon = dungeon;

        this.weapons = new ArrayList<>();
        this.keys = new ArrayList<>();

        Weapon basicSword = new Weapon("Basic Sword", 5);
        weapons.add(basicSword);
        equipped = basicSword;
    }

    /**
     * Get the equipped weapon of the player
     * @return The equipped weapon
     */
    public Weapon getEquippedWeapon(){
        return equipped;
    }

    /**
     * Add a equipment to the player's inventory. If it's a weapon and MAX_WEAPONS is reach, the drop mode are open for the player drop a weapon.
     * @param e Equipment to add to player's inventory
     */
    public void addItem(Equipment e){
        if(e instanceof Weapon){
            if(weapons.size() >= MAX_WEAPONS){
                dropMode = true;
                quitAfterDrop = true;
                itemToAdd = e;
                Console.println("Your inventory is full choose an item to drop");
                Console.println("Or press Q for cancel and lose this item");
                Console.println("Item in chest : " + e.getName() + " " + ((Weapon) e).getDamage() + " damages.");
                Console.line();
                print();
                dungeon.setSubListener(this);
                return;
            }
            weapons.add((Weapon) e);
            Console.println(e.getName() + " has been added to your inventory !");
        }else if(e instanceof Key){
            Key k = (Key) e;
            keys.add(k);
            Console.println("A " + k.getColor().getName().toLowerCase() + " key has been added to your inventory !");
        }
        Console.line();
    }

    /**
     * If a key are the same color that in the parameter return it else return null
     * @param color Color of desired key
     * @return A key if found, null if not
     */
    public Key getKey(KeyColor color){
        Key key = null;
        for(Key k : keys){
            if(k.getColor() == color) {
                key = k;
                break;
            }
        }

        if(key == null)
            return null;

        keys.remove(key);
        return key;
    }

    /**
     * Open this pane in the dungeon for the player can interact with his inventory.
     */
    public void openInventory(){
        dropMode = false;
        quitAfterDrop = false;
        print();

        dungeon.setSubListener(this);
    }

    /**
     * Print inventory content and inventory menu
     */
    @Override
    public void print(){
        if(dropMode){
            if(!quitAfterDrop) {
                Console.println("You are in drop mode choose the weapon you want to drop");
                Console.println("/!\\ A dropped weapon cannot be retrieved");
                Console.line();
            }
            printWeapons();
            Console.line();
            if(!quitAfterDrop) {
                Console.println("D : Quit drop mode");
                Console.println("Q : Close inventory");
            }else
                Console.println("Q : Cancel");
            return;
        }

        Console.println("You got " + dungeon.getPlayer().getHp() + " hp");
        Console.println("Your equiped weapon is " + equipped.getName() + " " + equipped.getDamage() + " damage");
        Console.line();
        Console.println("You open your inventory and you got :");

        printWeapons();

        Console.line();
        Console.println("Your keys :");
        Console.line();

        for (Key k : keys){
            Console.println("- " + k.getName());
        }

        Console.line();

        Console.println("0-"+(MAX_WEAPONS-1) +" : To take the corresponding weapon");
        Console.println("D : Drop a weapon");
        Console.println("Q : Close inventory");
    }

    /**
     * Print weapons with number for select it. And with "-" for equipped weapon.
     */
    private void printWeapons(){
        int cpt = 0;
        for(Weapon w : weapons) {
            Console.println((w == equipped ? "-" : cpt) + " : " + w.getName() + " " + w.getDamage() + " damages");
            cpt++;
        }
    }

    /**
     * Process the player input when this pane is open.
     * @param c Char to process
     */
    @Override
    public void select(char c) {
        if(c >= 48 && c <= 57){
            int slot = c-48;
            if(slot <= MAX_WEAPONS && slot < weapons.size() && !weapons.get(slot).equals(equipped)){
                if(!dropMode){
                    equipped = weapons.get(slot);
                }else{
                    Weapon toDrop = weapons.get(slot);
                    if(equipped.equals(toDrop)){
                        Console.println("This weapon is equiped you cannot drop it");
                    }else{
                        Console.println("You drop " + toDrop.getName());
                        weapons.remove(toDrop);
                    }

                    dropMode = false;
                    Console.line();

                    if(quitAfterDrop){
                        dungeon.removeSubListener();
                        quitAfterDrop = false;
                        if(weapons.size() < MAX_WEAPONS && itemToAdd instanceof Weapon)
                            weapons.add((Weapon) itemToAdd);
                        return;
                    }
                }
            }else {
                return;
            }
            print();
        }else if(c == 'Q'){
            dungeon.removeSubListener();
        }else if(c == 'D'){
            if(quitAfterDrop)
                return;
            dropMode = !dropMode;
            print();
        }else{
            print();
        }
    }
}
