/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.world.Entity;
import net.rowf.gaunt.world.dungeon.walls.Crumble;
import net.rowf.gaunt.world.entity.Prototype;

/**
 *
 * @author woeltjen
 */
public class Crumblers extends Walls {

    public Crumblers(Convertor<Index, Sprite> tiles) {
        super(tiles);
    }

    @Override
    public Prototype evaluate(Index key) {
        return new Crumbler(super.evaluate(key));
    }

    private static class Crumbler extends Prototype {
        private Prototype base;

        public Crumbler(Prototype base) {
            this.base     = base;
        }

        @Override
        public Entity spawn() {
            Entity e = base.spawn();
            e.add(new Crumble());
            return e;
        }

    }

}
