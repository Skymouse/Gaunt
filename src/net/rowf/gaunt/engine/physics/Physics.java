/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.physics;

import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Move;

/**
 *
 * @author woeltjen
 */
public class Physics implements Module {

    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Move m : e.get(Move.class))
                m.invoke(e, w);
    }

}
