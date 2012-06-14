/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon.walls;

import jgaunt.world.Component;

/**
 *
 * @author woeltjen
 */
public class Hardness implements Component {
    private int hardness;

    public Hardness(int hardness) {
        this.hardness = hardness;
    }

    public int get() {
        return hardness;
    }

}
