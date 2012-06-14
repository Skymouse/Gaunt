/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level;

import jgaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Populator {
    public Entity getEntity(int x, int y);
    public int    getWidth ();
    public int    getHeight();
}
