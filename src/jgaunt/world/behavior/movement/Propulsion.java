/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.behavior.movement;

import jgaunt.world.Boundary;
import jgaunt.world.Entity;
import jgaunt.world.Facing;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Move;

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
        for (Facing f : e.first(Facing.class))
            move (e, f.scale(speed));
    }

}
