/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.behavior.movement.Maneuver;
import net.rowf.gaunt.world.components.Speed;

/**
 *
 * @author woeltjen
 */
public class Pilot extends Control {

    public Pilot(Input input) {
        super(input);
    }

    @Override
    public int frequency() {
        return 1;
    }

    @Override
    public void react(Entity e, World w, Input i) {
        if (i.magnitude() > 0) {
            if (i.axes() >= 2) {
                float x = i.get(0) / i.magnitude();
                float y = i.get(1) / i.magnitude();
                float angle = (float) Math.atan2(y, x);
                e.set(Facing.class, new Facing(angle));
            }
        }
        float speed = 0;
        if (i.magnitude() > 0) // TODO: Move out of controls!
            for (Speed s : e.get(Speed.class))
                speed = Math.max(speed, s.get());
        e.set(Maneuver.class, speed > 0 ? Standard.WALK : Standard.STAND);
        
    }
    
}
