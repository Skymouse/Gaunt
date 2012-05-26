/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.engine.physics;

import jgaunt.engine.Module;
import jgaunt.world.Entity;
import jgaunt.world.Position;
import jgaunt.world.World;

/**
 *
 * @author woeltjen
 */
public class Physics implements Module {

    public void run(World w) {
        for (Entity e : w.getEntities())
            for (Position p : e.get(Position.class))
                e.set(Position.class, p.add(new Position(0.1f, 0.1f)));
    }

}
