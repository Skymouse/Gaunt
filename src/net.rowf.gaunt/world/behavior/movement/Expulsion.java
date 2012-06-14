/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior.movement;

import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.Position;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Impact;

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

        public Impulse(Position inertia) {
            super(inertia);
        }

        @Override
        public void invoke(Entity e, World argument) {
            super.invoke(e, argument);
            e.remove(this);
        }

    }
   
}
