/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior;

import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.Position;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Move;

/**
 *
 * @author woeltjen
 */
public class Velocity implements Move {
    private Position inertia;

    public Velocity(Position inertia) {
        this.inertia = inertia;
    }

    public void invoke(Entity e, World argument) {
        //TODO: Check for elapsed ticks?
        for (Boundary b : e.first(Boundary.class))
            e.set(Boundary.class, b.add(inertia));
    }

}
