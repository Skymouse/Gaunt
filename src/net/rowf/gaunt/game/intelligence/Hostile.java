/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.world.components.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.adventurer.Playable;
import net.rowf.gaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public class Hostile implements Think {    
    private int period;
    public Hostile(int period) {
        this.period = period;
    }

    @Override
    public void invoke(Entity e, World w) {
        //TODO Encode vision
        if (w.getTicks() % period == 0) 
            for (Entity other : w.getEntities())                
                if (!other.get(Playable.class).isEmpty())
                    for (Boundary b : e.get(Boundary.class))
                        for (Boundary t : other.get(Boundary.class))
                            if (b.center().add(t.center().scale(-1f)).length() < 5f)
                                e.set(Target.class, new Target(other));
    }
    
}
