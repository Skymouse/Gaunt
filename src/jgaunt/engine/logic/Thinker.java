/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.logic;

import jgaunt.engine.Module;
import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public class Thinker implements Module {

    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Think t : e.get(Think.class))
                t.invoke(e, w);
    }

}
