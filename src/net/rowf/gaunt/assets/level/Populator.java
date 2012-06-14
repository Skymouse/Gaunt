/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level;

import net.rowf.gaunt.world.Entity;

/**
 *
 * @author woeltjen
 */
public interface Populator {
    public Entity getEntity(int x, int y);
    public int    getWidth ();
    public int    getHeight();
}
