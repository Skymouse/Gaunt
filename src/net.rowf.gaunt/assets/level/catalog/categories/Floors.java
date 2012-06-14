/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.catalog.categories;

import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Prototype;
import jgaunt.world.dungeon.Floor;

/**
 *
 * @author woeltjen
 */
public class Floors extends Category {
    private Convertor<Index, Sprite> tiles;

    public Floors(Convertor<Index, Sprite> tiles) {
        this.tiles = tiles;
    }

    public Prototype convert(Index key) {
        return new Floor(tiles.convert(key));
    }

}
