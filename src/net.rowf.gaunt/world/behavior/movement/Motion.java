/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.world.behavior.movement;

import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.Position;

/**
 *
 * @author woeltjen
 */
public abstract class Motion {
    public void move (Entity e, Position p) {
        for (Boundary b : e.first(Boundary.class))
            e.set(Boundary.class, b.add(p));
    }
}
