/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon.doors;

import net.rowf.gaunt.world.Component;

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
