/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.world.dungeon;

import java.util.Arrays;
import net.rowf.gaunt.world.Boundary;
import net.rowf.gaunt.world.Component;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.World;
import net.rowf.gaunt.world.behavior.Common.Think;

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

        @Override
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
