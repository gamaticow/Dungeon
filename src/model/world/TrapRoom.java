package model.world;
/*
 *   Created by Corentin on 26/05/2020 at 11:40
 */

public class TrapRoom extends Room {

    private static final int TRAP_DAMAGE = 2;
    private boolean trapEnable;

    /**
     * Constructor of the TrapRoom
     */
    public TrapRoom(){
        trapEnable = true;
    }

    /**
     * Get if the trap is enable or not.
     * @return The state of the trap in this room.
     */
    public boolean isTrapEnable(){
        return trapEnable;
    }

    /**
     * Method for disable the trap in this room.
     */
    public void disableTrap(){
        trapEnable = false;
    }

    /**
     * Get the trap damage.
     * @return The damage of the trap.
     */
    public int getTrapDamage(){
        return TRAP_DAMAGE;
    }

}
