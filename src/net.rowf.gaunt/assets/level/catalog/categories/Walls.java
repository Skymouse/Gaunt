/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.catalog.categories;

import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Prototype;
import jgaunt.world.dungeon.Wall;

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
