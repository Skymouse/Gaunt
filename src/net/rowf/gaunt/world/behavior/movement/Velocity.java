/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior.movement;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Position;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;

/**
 *
 * @author woeltjen
 */
public class Velocity implements Move {
    private Position inertia;

    public Velocity(Position inertia) {
        this.inertia = inertia;
    }

    @Override
    public void invoke(Entity e, World argument) {
        //TODO: Check for elapsed ticks?
        for (Boundary b : e.first(Boundary.class))
            e.set(Boundary.class, b.add(inertia));
    }

}
