package model.world;
/*
 *   Created by Corentin on 04/05/2020 at 15:04
 */

public enum Direction {

    NORTH   ('N'),
    EAST    ('E'),
    SOUTH   ('S', NORTH),
    WEST    ('W', EAST);

    private char keyCode;
    private Direction opposite;

    /**
     * Direction constructor for initialize local attributes.
     * @param keyCode Char link to this direction on the keyboard
     * @param opposite Opposite of this direction, set this as opposite for the opposite direction too.
     */
    Direction(char keyCode, Direction opposite){
        this(keyCode);
        this.opposite = opposite;
        opposite.opposite = this;
    }

    /**
     * Direction constructor for initialize local attributes.
     * @param keyCode Char link to this direction on the keyboard
     */
    Direction(char keyCode){
        this.keyCode = keyCode;
    }

    /**
     * Get the opposite direction.
     * @return The opposite direction
     */
    public Direction getOpposite(){
        return opposite;
    }

    /**
     * Get the char keycode for print it in ({@link Room#printRoom()} or compare to keyboard
     * @return The key code of the direction.
     */
    public char getKeyCode(){
        return keyCode;
    }

    /**
     * Static method for get the direction with the keycode pass in parameter.
     * @param c Keycode for find a direction
     * @return The direction if the direction exist, else return null.
     */
    public static Direction getCardinalityByChar(char c){
        for(Direction ca : Direction.values()){
            if(ca.keyCode == c)
                return ca;
        }
        return null;
    }

}
