/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic.control;

import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Task;

/**
 *
 * @author woeltjen
 */
public abstract class Control implements Task {
    private Input input;
    private int   next = 0;
    
    public Control (Input input) {
        this.input = input;
    }
    
    @Override
    public void invoke(Entity e, World w) {
        if (w.getTicks() > next) { 
            input.poll();
            react (e, w, input);
            next += frequency();
        }
    }
    
    public abstract void react(Entity e, World w, Input i);
    public abstract int  frequency();

    
    
//    private Entity attached = null;
//    private String target;
//    
//    public Controller(String target) {
//        this.target = target;
//    }
//    
//    @Override
//    public void run(World w) {
//        if (attached == null)
//            for (Entity e : w.getEntities())
//                for (Player p : e.get(Player.class))
//                    if (p.getName().equals(target))
//                        attached = e;
//        //if (attached != null)
//            
//    }
//    
}
