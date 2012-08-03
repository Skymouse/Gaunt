/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control;

import net.rowf.gaunt.theory.Function;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Behavior;

/**
 *
 * @author woeltjen
 */
public class Regulated extends Invocation {
    private Function<Integer, Entity> regulator;
    private int                       frequency;

    public Regulated(Function<Integer, Entity> regulator, Class<Behavior<World>> invocation, Input input) {
        super(invocation, input, 1);
        this.frequency = 1;
        this.regulator = regulator;
    }

    @Override
    public int frequency() {
        return frequency;
    }

    @Override
    public void react(Entity e, World w, Input i) {
        super.react(e, w, i);
        frequency = regulator.evaluate(e);
    }
    
    
    
    
}
