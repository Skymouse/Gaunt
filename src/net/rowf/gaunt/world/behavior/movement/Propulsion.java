/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior.movement;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;

/**
 *
 * @author woeltjen
 */
public class Propulsion extends Motion implements Move {
    private float speed;

    public Propulsion (float speed) {
        this.speed = speed;
    }

    public void invoke(Entity e, World argument) {
        if (speed > 0)
            for (Facing f : e.first(Facing.class))
                move (e, f.scale(speed));
    }

}
