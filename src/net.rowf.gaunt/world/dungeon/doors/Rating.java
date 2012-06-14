/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon.doors;

import jgaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public class Rating implements Component {
    private int rating;

    public Rating (int rating) {
        this.rating = rating;
    }

    public int get() {
        return rating;
    }
}
