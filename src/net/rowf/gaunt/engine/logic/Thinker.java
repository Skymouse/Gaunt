/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.engine.logic;

import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public class Thinker implements Module {

    @Override
    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Think t : e.get(Think.class))
                t.invoke(e, w);
    }
    
}
