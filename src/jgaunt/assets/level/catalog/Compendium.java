/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgaunt.assets.level.catalog;

import java.util.ArrayList;
import java.util.List;
import jgaunt.assets.Depot;
import jgaunt.assets.level.Convertor;
import jgaunt.assets.level.Index;
import jgaunt.assets.level.catalog.categories.Category;
import jgaunt.assets.level.catalog.categories.Floors;
import jgaunt.assets.level.catalog.categories.Walls;
import jgaunt.engine.renderer.Animation;
import jgaunt.engine.renderer.Gallery;
import jgaunt.engine.renderer.Row;
import jgaunt.engine.renderer.Sprite;
import jgaunt.world.Entity;
import jgaunt.world.Prototype;

/**
 *
 * @author woeltjen
 */
public class Compendium {
    private Depot depot;

    public Compendium(Depot depot) {
        this.depot = depot;
    }

    public Catalog<Prototype> getCatalog() {
        List<Category> categories = new ArrayList<Category>();

        //TODO: Move this highly coupled stuff somewhere ???
        Gallery tiles = depot.retrieve(Gallery.class, "wall_tiles");
        categories.add(new Floors(makeTileset(new Row(tiles, 0))));
        categories.add(new Walls (makeTileset(new Row(tiles, 1))));
        categories.add(new Walls (makeTileset(new Row(tiles, 2))));

        return new Catalog(categories, NONENTITY, 256);
    }


    private Convertor<Index, Sprite> makeTileset (final Animation anim) {
        return new Convertor<Index, Sprite>() {
            public Sprite convert(Index key) {
                return anim.getSprite(key.get() % anim.frames());
            }
            public Index maximum() {
                return new Index(anim.frames());
            };
        };
    }

    private Convertor<Index, Sprite> makeTileset (final Gallery gallery) {
        return new Convertor<Index, Sprite>() {
            public Sprite convert(Index key) {
                int index  = key.get();
                int row    = index % gallery.columns();
                int column = index / gallery.columns();
                return gallery.getSprite(row, column);
            }

            public Index maximum() {
                return new Index(gallery.rows() * gallery.columns());
            };
        };
    }

    private static final Prototype NONENTITY  = new Prototype() {

        @Override
        public Entity spawn() {
            return new Entity();
        }

    };

}
