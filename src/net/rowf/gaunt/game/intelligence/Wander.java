/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.components.Facing;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;
import net.rowf.gaunt.world.behavior.Common.Think;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.behavior.movement.Maneuver;

/**
 *
 * @author woeltjen
 */
public class Wander implements Think {
    private int period;
    private int delay;
    public Wander(int period, int delay) {
        this.period = period;
        this.delay  = delay;
    }
    
    public Wander(int period) {
        this(period, period / 2);
    }

    @Override
    public void invoke(Entity e, World w) {
        boolean waiting = e.get(Move.class).contains(Standard.STAND);
        
        long chance = waiting ? delay : period;
                
        if (w.getTicks() % chance == 0) {
            if (waiting) 
                e.set(Facing.class, new Facing((float) (Math.random() * Math.PI * 2)));
            e.set(Maneuver.class, waiting ? Standard.WALK : Standard.STAND );
        }
    }
    
    
}
