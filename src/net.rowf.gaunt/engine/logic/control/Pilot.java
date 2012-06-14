/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.engine.logic.control;

import jgaunt.world.Entity;
import jgaunt.world.Facing;
import jgaunt.world.World;
import jgaunt.world.behavior.movement.Propulsion;
import jgaunt.world.components.Speed;

/**
 *
 * @author woeltjen
 */
public class Pilot extends Controller {

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
        if (i.magnitude() > 0)
            for (Speed s : e.get(Speed.class))
                speed = Math.max(speed, s.get());
        e.set(Propulsion.class, new Propulsion(speed / 100f));
        
    }
    
}
