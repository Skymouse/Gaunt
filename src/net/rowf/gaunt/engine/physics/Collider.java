/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.engine.physics;

import net.rowf.gaunt.engine.Module;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Collide;

/**
 *
 * @author woeltjen
 */
public class Collider implements Module {

    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Collide c : e.get(Collide.class))
                for (Entity other : w.getEntities())
                    if (!e.equals(other))
                        c.invoke(e, other);
    }

}
