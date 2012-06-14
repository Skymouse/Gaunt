/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgaunt.engine.logic.control;

import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public abstract class Controller implements Think {
    private Input input;
    private int   next = 0;
    
    public Controller (Input input) {
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
