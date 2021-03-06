/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rowf.gaunt.assets.level.catalog.categories;

import net.rowf.gaunt.theory.Convertor;
import net.rowf.gaunt.assets.level.Index;
import net.rowf.gaunt.engine.renderer.Sprite;
import net.rowf.gaunt.world.entity.Prototype;
import net.rowf.gaunt.world.dungeon.Floor;

/**
 *
 * @author woeltjen
 */
public class Floors extends Category {
    private Convertor<Index, Sprite> tiles;

    public Floors(Convertor<Index, Sprite> tiles) {
        this.tiles = tiles;
    }

    public Prototype evaluate(Index key) {
        return new Floor(tiles.evaluate(key));
    }

}
