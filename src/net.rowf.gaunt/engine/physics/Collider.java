/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.physics;

import jgaunt.engine.Module;
import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Collide;

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
