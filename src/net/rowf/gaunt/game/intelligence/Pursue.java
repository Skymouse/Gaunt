/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.game.intelligence;

import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Facing;
import net.rowf.gaunt.world.Vector;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;
import net.rowf.gaunt.world.behavior.Common.Think;
import net.rowf.gaunt.world.behavior.Standard;
import net.rowf.gaunt.world.behavior.movement.Maneuver;

/**
 *
 * @author woeltjen
 */
public class Pursue implements Think {
    private int period;
    
    public Pursue(int period) {
        this.period = period;
    }
    
    @Override
    public void invoke(Entity e, World w) {
        boolean waiting = e.get(Move.class).contains(Standard.STAND);
        
        if (w.getTicks() % period == 0) {
            for (Target t : e.get(Target.class))
                e.set(Facing.class, face(e, t.get()));
            e.set(Maneuver.class, Standard.WALK);
        }
    }
    
    private Facing face(Entity source, Entity target) {
        for (Boundary b : source.first(Boundary.class))
            for (Boundary t : target.first(Boundary.class))
                return new Facing(angle(t.center().add(b.center().scale(-1f))));
        return new Facing(0f);
    }
    
    private float angle (Vector v) {
        return (float) Math.atan2(v.getY(), v.getX());
    }
    
}
