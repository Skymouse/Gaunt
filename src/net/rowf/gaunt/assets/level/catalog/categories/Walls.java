/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.assets.level.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.world.Prototype;
import net.rowf.gaunt.world.dungeon.Wall;

/**
 *
 * @author woeltjen
 */
public class Walls extends Category {
    private Convertor<Index, Sprite> tiles;

    public Walls(Convertor<Index, Sprite> tiles) {
        this.tiles = tiles;
    }

    public Prototype convert(Index key) {
        int index = key.get();

        if (index > 0) return new Wall(tiles.convert(key), index);
        else           return new Wall(tiles.convert(key));
    }

}
