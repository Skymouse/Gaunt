/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior.movement;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Impact;

/**
 *
 * @author woeltjen
 */
public class Expulsion implements Impact {

    public void invoke(Entity e, Entity other) {
        for (Boundary b1 : e.get(Boundary.class))
            for (Boundary b2 : other.get(Boundary.class))
                e.add(new Impulse(b2.eject(b1)));

    }

    private class Impulse extends Velocity {

        public Impulse(Vector inertia) {
            super(inertia);
        }

        @Override
        public void invoke(Entity e, World argument) {
            super.invoke(e, argument);
            e.remove(this);
        }

    }
   
}
