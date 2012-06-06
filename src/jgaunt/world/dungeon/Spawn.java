/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.world.dungeon;

import java.util.Arrays;
import jgaunt.world.Boundary;
import jgaunt.world.Component;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;
import jgaunt.world.World;
import jgaunt.world.behavior.Common.Think;

/**
 *
 * @author woeltjen
 */
public class Spawn extends Prototype {
    private Prototype prototype;

    public Spawn (Prototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public Entity spawn() {
        return new Entity(Arrays.<Component>asList(new Generator()));
    }

    private class Generator implements Think {

        public void invoke(Entity e, World w) {
            Entity   spawn = prototype.spawn();

            for (Boundary location : e.get(Boundary.class)) {
                for (Boundary b : spawn.first(Boundary.class))
                    location = b.add(location.getMinimum());
                spawn.set(Boundary.class, location);
            }

            w.addEntity(spawn);

            for (Dungeon d : e.get(Dungeon.class))
                d.clearEntity(e);
        }

    }

}
