package model.exceptions;
/*
 *   Created by Corentin on 05/05/2020 at 19:28
 */

import model.equipement.KeyColor;

/**
 * Exception throw if {@link model.panes.ConfirmUseKey} is called with a key and door of different colours.
 */
public class KeyNotCompatibleException extends Exception {

    public KeyNotCompatibleException(KeyColor door, KeyColor key){
        super("The " + key.getName() + " key is not compatible on a " + door.getName() + " door.");
    }

}
