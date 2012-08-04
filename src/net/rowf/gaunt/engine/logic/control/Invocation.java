/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Behavior;

/**
 *
 * @author woeltjen
 */
public class Invocation extends Control {
    private Class<? extends Behavior<World>> invocation;
    private int                    frequency;

    public Invocation(Class<? extends Behavior<World>> invocation, Input input, int frequency) {
        super(input);
        this.invocation = invocation;
        this.frequency  = frequency;
    }

    @Override
    public int frequency() {
        return frequency;
    }

    @Override
    public void react(Entity e, World w, Input i) {
        if (i.magnitude() > 0)
            for (Behavior<World> b : e.get(invocation))
                b.invoke(e, w);
    }
    
    
            
}
