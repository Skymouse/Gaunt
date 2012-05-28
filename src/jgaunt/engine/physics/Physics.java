/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.physics;

import jgaunt.engine.Module;
import jgaunt.world.Entity;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Move;

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
