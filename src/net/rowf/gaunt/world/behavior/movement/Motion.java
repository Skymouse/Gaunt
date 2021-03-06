/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.world.behavior.movement;

import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;

/**
 *
 * @author woeltjen
 */
public abstract class Motion {
    public void move (Entity e, Vector p) {
        for (Boundary b : e.first(Boundary.class))
            e.set(Boundary.class, b.add(p));
    }
}
