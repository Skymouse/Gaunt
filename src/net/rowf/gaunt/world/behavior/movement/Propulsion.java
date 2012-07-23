/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.behavior.movement;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;
import net.rowf.gaunt.world.components.Speed;

/**
 *
 * @author woeltjen
 */
public class Propulsion extends Motion implements Maneuver {

    private float factor;
    
    public Propulsion () {
        this(0.01f);
    }
    
    public Propulsion (float factor) {
        this.factor = factor;
    }
    
    public void invoke(Entity e, World argument) {
        float speed = 0f;
        for (Speed s : e.get(Speed.class))
            speed = s.get() * factor;
        
        if (speed > 0)
            for (Facing f : e.first(Facing.class))
                move (e, f.scale(speed));
    }

}
